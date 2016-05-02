package com.despegar.university.exercises.concurrence.domain.services;

import javax.annotation.PostConstruct;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.despegar.cfa.library.zookeeperrecipes.recipes.semaphore.Semaphore;
import com.despegar.cfa.library.zookeeperrecipes.recipes.semaphore.SemaphoreManager;
import com.despegar.cfa.library.zookeeperrecipes.recipes.sharedvalue.SharedValueManager;
import com.despegar.university.exercises.concurrence.domain.exceptions.BookingServiceException;
import com.despegar.university.exercises.concurrence.domain.utils.DateUtils;

@Component
public class KeyMaker {

    private static final String REQUEST_ID_FORMAT = "%s-%s";
    private static final String HOTEL_KEY_FORMAT = "%s | %s";
    private static final String LAST_ID = "bookingId";

    @Autowired
    private SharedValueManager sharedValueManager;
    @Autowired
    private DateUtils dateUtils;
    @Autowired
    private SemaphoreManager semaphoreManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyMaker.class);

    @PostConstruct
    public void init() {
        Integer id = this.sharedValueManager.get(LAST_ID);

        if (id == null) {
            this.sharedValueManager.save(LAST_ID, 1);
        }
    }

    public String makeBookingId(String requestId) {
        Semaphore semaphore = this.semaphoreManager.create("last_id");
        if (semaphore.lock()) {
            try {
                Integer value = this.sharedValueManager.get(LAST_ID);
                value++;
                this.sharedValueManager.save(LAST_ID, value);
                LOGGER.info("New booking id generated: {}", value);
                return String.format(REQUEST_ID_FORMAT, requestId, value);
            } finally {
                semaphore.unlock();
            }

        } else {
            throw new BookingServiceException("Semaphore couldnt be acquired.");
        }
    }

    public String makeHotelKey(LocalDateTime date, String hotelId) {
        return String.format(HOTEL_KEY_FORMAT, DateUtils.toString(date), hotelId);
    }
}
