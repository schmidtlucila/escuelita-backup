package tp.tests.impl.testng.flights.smoketests.site;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.despegar.automation.commons.config.model.Environment;
import com.despegar.automation.commons.utils.CsvUtils;

import tp.flights.model.FlightsSearchGuiData;
import tp.flights.site.FlightsCheckoutPage;
import tp.flights.site.FlightsHomePage;
import tp.flights.site.FlightsSearchResultPage;
import tp.flights.site.SearchResult;
import tp.services.flights.fsm.searchmethods.FlightsSearchFull;
import tp.services.flights.fsm.searchmethods.request.FlightsSearchFullRequest;
import tp.services.flights.fsm.searchmethods.response.FlightsSearchFullResponse;
import tp.services.flights.fsm.searchmethods.response.ResultItem;
import tp.tests.impl.testng.configuration.ConfigurationLoaderGui;
import tp.tests.impl.testsng.flights.model.FlightsSmokeTestData;

public class FlightsGuiTest
    extends ConfigurationLoaderGui {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightsGuiTest.class.getName());

    @Test(dataProvider = "data")
    public void testHome(FlightsSmokeTestData smokeTestData) {
        Environment environment = this.configuration.getEnvironment();
        this.configuration.getOfficeData();
        Integer.parseInt(this.configuration.getEnvironment().getKeys().get("flights_despegar_site_timeout"));


        FlightsHomePage flightsHomePage = new FlightsHomePage(this.configuration, this.driver,
            Integer.parseInt(environment.getKeys().get("flights_despegar_site_timeout")));
        FlightsSearchGuiData flightsSearchGuiData = this.flightsSearchGuiDataBasedOn(smokeTestData);

        flightsHomePage.go();
        FlightsSearchResultPage resultPage = flightsHomePage.search(flightsSearchGuiData);
        this.changeOfEnvironment();
        resultPage.collectResultsInformation();

        SearchResult firstResult = resultPage.getFirstResult();


        Assert.assertNotNull(firstResult.getFare());
        if ("flights_despegar_ar".equals(this.configuration.getOfficeData().getName())) {
            int sumOfPartialPrices = firstResult.getAfip() + firstResult.getCharges() + firstResult.getTaxes()
                + firstResult.getPriceOfBabies() + firstResult.getPriceOfChildren() + firstResult.getPriceOfAdults();
            Assert.assertEquals(firstResult.getTotalPrice(), sumOfPartialPrices);
        }


        FlightsSearchFull flightSearch = new FlightsSearchFull(this.configuration);
        FlightsSearchFullRequest searchFullRequest = this.searchFullRequestBasedOn(smokeTestData);

        FlightsSearchFullResponse dataSearchResult = flightSearch.search(searchFullRequest);

        List<ResultItem> items = dataSearchResult.getItems();
        Assert.assertNotNull(items);
        Assert.assertEquals(items.get(0).getItinerary().getItineraryPrice().getAll().get(0).getAdult().getQuantity(),
            searchFullRequest.getAdults());
        if (searchFullRequest.getChildren() > 0) {
            Assert.assertEquals(items.get(0).getItinerary().getItineraryPrice().getAll().get(0).getChild().getQuantity(),
                searchFullRequest.getChildren());
        }
        if (searchFullRequest.getInfants() > 0) {
            Assert.assertEquals(items.get(0).getItinerary().getItineraryPrice().getAll().get(0).getInfant().getQuantity(),
                searchFullRequest.getInfants());
        }

        resultPage.selectFirstOptionsOfResult();
        FlightsCheckoutPage checkoutPage = resultPage.clickBuyButtonFirstResult();

        Assert.assertEquals(this.getAdultTicketsOf(flightsSearchGuiData), checkoutPage.getAmountOfAdultSections());
        Assert.assertEquals(this.getChildTicketsOf(flightsSearchGuiData), checkoutPage.getAmountOfChildSections());
        Assert.assertEquals(this.getBabyTicketsOf(flightsSearchGuiData), checkoutPage.getAmountOfBabySections());

        Integer cheapestPriceBack = this.getCheapestItem(items);
        Assert.assertTrue(Math.abs(cheapestPriceBack.intValue() - firstResult.getFare()) < 10);

    }


    private Integer getCheapestItem(List<ResultItem> items) {
        Integer cheapestPrice = null;
        for (int i = 0; i < items.size(); i++) {
            int price = items.get(i).getItinerary().getItineraryPrice().getAll().get(0).getAdult().getBaseFare();
            if (cheapestPrice == null) {
                cheapestPrice = Integer.valueOf(price);
            }
            if (price < cheapestPrice) {
                cheapestPrice = Integer.valueOf(price);
            }
        }

        return cheapestPrice;
    }


    private int getAdultTicketsOf(FlightsSearchGuiData flightsSearchGuiData) {
        int n = Integer.parseInt(flightsSearchGuiData.getNumberOfAdults());
        for (String age : flightsSearchGuiData.getChildrenAges()) {
            if (age.contains(this.configuration.getEnvironment().getKeys().get("adult.child.code")) && !"NA".equals(age)) {
                n++;
            }
        }

        return n;
    }

    private int getChildTicketsOf(FlightsSearchGuiData flightsSearchGuiData) {
        int n = 0;
        for (String age : flightsSearchGuiData.getChildrenAges()) {
            if (age.contains(this.configuration.getEnvironment().getKeys().get("child.code"))) {
                n++;
            }
            if (age.contains(this.configuration.getEnvironment().getKeys().get("infant.code"))) {
                n++;
            }
        }

        return n;
    }

    private int getBabyTicketsOf(FlightsSearchGuiData flightsSearchGuiData) {
        int n = Integer.parseInt(flightsSearchGuiData.getNumberOfAdults());
        for (String age : flightsSearchGuiData.getChildrenAges()) {
            if (age.contains(this.configuration.getEnvironment().getKeys().get("baby.code"))) {
                n++;
            }
        }

        return n;
    }


    private FlightsSearchFullRequest searchFullRequestBasedOn(FlightsSmokeTestData smokeTestData) {
        FlightsSearchFullRequest request = new FlightsSearchFullRequest();
        request.setAdults(this.getAdultTicketsOf(smokeTestData));
        request.setBrand(0);
        request.setProduct(0);
        request.setChannel("site");
        request.setChildren(this.getChildTicketsOf(smokeTestData));
        request.setInfants(this.getInfantTicketsOf(smokeTestData));
        request.setItemType("SINGLETYPE");
        request.setFrom(smokeTestData.getOrigin());
        request.setTo(smokeTestData.getDestination());
        request.setDepartureDate(new DateTime(smokeTestData.getDateFrom()));
        request.setSearchType(smokeTestData.getSearchType());
        if (!"oneway".equals(smokeTestData.getSearchType())) {
            request.setReturnDate(new DateTime(smokeTestData.getDateTo()));
            request.setCountry(this.configuration.getOfficeData().getKeys().get("country"));
        }
        request.setFareSelector("TOTALFARE");
        request.setChannel("site");

        return request;
    }

    private int getInfantTicketsOf(FlightsSmokeTestData smokeTestData) {
        int n = 0;
        for (String childrenAge : this.getChildrenAgesFrom(smokeTestData)) {
            if ("M".equals(childrenAge)) {
                n++;
            }

        }
        return n;
    }

    private int getAdultTicketsOf(FlightsSmokeTestData smokeTestData) {
        int n = Integer.parseInt(smokeTestData.getNumberOfAdults());
        for (String childrenAge : this.getChildrenAgesFrom(smokeTestData)) {
            if ("A".equals(childrenAge)) {
                n++;
            }

        }
        return n;
    }

    private int getChildTicketsOf(FlightsSmokeTestData smokeTestData) {
        int n = 0;
        for (String childrenAge : this.getChildrenAgesFrom(smokeTestData)) {
            if ("C".equals(childrenAge) || "I".equals(childrenAge)) {
                n++;
            }

        }
        return n;
    }

    private FlightsSearchGuiData flightsSearchGuiDataBasedOn(FlightsSmokeTestData smokeTestData) {
        FlightsSearchGuiData flightsSearchGuiData = new FlightsSearchGuiData();
        flightsSearchGuiData.setOrigin(smokeTestData.getOrigin());
        flightsSearchGuiData.setDestination(smokeTestData.getDestination());
        flightsSearchGuiData.setDateFrom(smokeTestData.getDateFrom());
        flightsSearchGuiData.setDateTo(smokeTestData.getDateTo());
        flightsSearchGuiData.setNumberOfAdults(smokeTestData.getNumberOfAdults());
        flightsSearchGuiData.setNumberOfChildren(smokeTestData.getNumberOfChildren());
        flightsSearchGuiData.setOneWay("oneway".equals(smokeTestData.getSearchType()));

        flightsSearchGuiData.setChildrenAges(this.getChildrenAgesFrom(smokeTestData));

        return flightsSearchGuiData;
    }



    private List<String> getChildrenAgesFrom(FlightsSmokeTestData smokeTestData) {
        List<String> childrenAges = new ArrayList<String>(7);
        childrenAges.add(smokeTestData.getChildrenAge1());
        childrenAges.add(smokeTestData.getChildrenAge2());
        childrenAges.add(smokeTestData.getChildrenAge3());
        childrenAges.add(smokeTestData.getChildrenAge4());
        childrenAges.add(smokeTestData.getChildrenAge5());
        childrenAges.add(smokeTestData.getChildrenAge6());
        childrenAges.add(smokeTestData.getChildrenAge7());

        return childrenAges;
    }

    @DataProvider(name = "data")
    public Iterator<FlightsSmokeTestData[]> data() {
        String fileName = this.configuration.getEnvironment().getKeys().get("site_smoke_test_data_file");
        LOGGER.info("File Name: " + fileName);

        File fileCSVToRead = this.executionContextApp.getTestsData().get(fileName);

        CellProcessor[] processors = new CellProcessor[] {
            new NotNull(), new NotNull(), new NotNull(), new NotNull(), new Optional(), new NotNull(), new NotNull(),
            new NotNull(), new Optional(), new Optional(), new Optional(), new Optional(), new Optional(), new Optional(),
            new Optional()};

        List<FlightsSmokeTestData> results = CsvUtils.readCsvFileToBean(FlightsSmokeTestData.class, processors,
            fileCSVToRead);

        List<FlightsSmokeTestData[]> dataList = new ArrayList<FlightsSmokeTestData[]>();

        for (FlightsSmokeTestData smokeTestData : results) {
            if (smokeTestData.getOfficeData().equalsIgnoreCase(this.configuration.getOfficeData().getName())) {
                dataList.add(new FlightsSmokeTestData[] {smokeTestData});
            }
        }
        return dataList.iterator();
    }

    private void changeOfEnvironment() {
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String temp = this.driver.getCurrentUrl().split("http://")[1];
        int index = temp.indexOf('/');
        temp = temp.substring(index + 1);
        this.driver.get(this.builderSecondUrl() + temp);

    }

    private String builderSecondUrl() {
        String officeCode = this.configuration.getOfficeData().getName();
        String key = officeCode + "_gui_flightsHome";
        return this.secondEnvironment.getKeys().get(key);
    }


}
