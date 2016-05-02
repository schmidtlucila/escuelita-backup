package com.despegar.university.exercises.concurrence.domain.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.despegar.cfa.library.zookeeperrecipes.recipes.semaphore.Semaphore;
import com.despegar.cfa.library.zookeeperrecipes.recipes.semaphore.SemaphoreManager;
import com.despegar.university.exercises.concurrence.domain.dao.BookingValuesDAO;
import com.despegar.university.exercises.concurrence.domain.exceptions.BookingServiceException;
import com.despegar.university.exercises.concurrence.domain.model.BookingRequestDTO;
import com.despegar.university.exercises.concurrence.domain.model.HotelInfo;
import com.despegar.university.exercises.concurrence.domain.utils.DateUtils;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

@Service
public class BookingService {

    private static final String PROCESSING_REQUEST = "Processing booking request %s";

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);

    private static Predicate<HotelInfo> DISPONIBILITY_PREDICATE = new Predicate<HotelInfo>() {

        @Override
        public boolean apply(HotelInfo input) {
            return input.hasDisponibility();
        }
    };

    @Autowired
    private BookingValuesDAO bookingDAO;
    @Autowired
    private HotelsService hotelsService;
    @Autowired
    private KeyMaker keyMaker;
    @Autowired
    private SemaphoreManager semaphoreManager;

    public String reserve(BookingRequestDTO request) {
        LOGGER.info(String.format(PROCESSING_REQUEST, request));
        List<Semaphore> semaphores = new ArrayList<Semaphore>();
        List<LocalDateTime> days = DateUtils.getDatesRange(request.getSince(), request.getUntil());
        List<HotelInfo> hotels = this.hotelsService.getHotelsInfoForDays(request.getHotelId(), days);

        for (HotelInfo hotel : hotels) {

            LOGGER.info("Semaphore to lock: {}", hotel.getId());
            Semaphore semaphore = this.semaphoreManager.create(hotel.getId());
            semaphores.add(semaphore);
        }

        if (this.allSemaphoresAreLocked(semaphores)) {
            LOGGER.info("All semaphores are acquired :)");
            try {
                boolean hasDisponibility = Iterables.all(hotels, DISPONIBILITY_PREDICATE);

                if (hasDisponibility) {
                    LOGGER.info("Rooms available for requested days.");
                    String bookingId = this.keyMaker.makeBookingId(request.getRequestId());
                    this.doReserve(hotels, bookingId);

                    this.bookingDAO.save(bookingId, request);
                    this.hotelsService.saveHotels(hotels);

                    LOGGER.info("Successfull booking.");

                    return bookingId;
                } else {
                    throw new BookingServiceException("No rooms available for one or more days.");
                }

            } finally {
                this.unlockAllSemaphores(semaphores);
                semaphores.clear();
            }

        } else {
            throw new BookingServiceException("Couldn't acquire semaphores!");
        }

    }

    private boolean allSemaphoresAreLocked(List<Semaphore> semaphores) {

        for (Semaphore semaphore : semaphores) {
            LOGGER.info("Checking state of semaphore... {}", semaphore.getResourcePath());
            if (!semaphore.lock()) {
                return false;
            }
        }

        return true;

    }

    private void unlockAllSemaphores(List<Semaphore> semaphores) {

        for (Semaphore semaphore : semaphores) {
            semaphore.unlock();
        }

    }



    private void doReserve(List<HotelInfo> hotels, String bookingId) {
        for (HotelInfo hotel : hotels) {
            hotel.getBookings().add(bookingId);
        }
    }

    public BookingRequestDTO searchBooking(String id) {
        return this.bookingDAO.get(id);
    }

}
