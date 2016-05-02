package tp.flights.site;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.despegar.automation.commons.config.model.Configuration;

import tp.flights.model.FlightsSearchGuiData;

public class FlightsHomePage
    extends FlightsSitePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightsHomePage.class);
    private static final String PLACEHOLDER = "placeholder";

    @FindBy(id = "nibbler-facebook-overlay")
    private WebElement popup;

    @FindBy(className = "nibbler-common-overlay-close")
    private WebElement closePopupButton;

    @FindBy(id = "flights-going")
    private WebElement flightsGoing;

    @FindBys({@FindBy(className = "sbox-flights-container"), @FindBy(className = "sbox-origin")})
    private WebElement origin;

    @FindBys({@FindBy(className = "sbox-flights-container"), @FindBy(className = "sbox-destination")})
    private WebElement destination;

    @FindBys({@FindBy(className = "sbox-flights-container"), @FindBy(className = "sbox-datein")})
    private WebElement dateFrom;

    @FindBys({@FindBy(className = "sbox-flights-container"), @FindBy(className = "sbox-dateout")})
    private WebElement dateTo;

    @FindBys({@FindBy(className = "sbox-flights-container"), @FindBy(className = "sbox-passengers-adults-select")})
    private WebElement adults;

    @FindBys({@FindBy(className = "sbox-flights-container"), @FindBy(className = "sbox-passengers-childrens-select")})
    private WebElement children;

    @FindBys({@FindBy(className = "sbox-flights-container"), @FindBy(className = "sbox-ui-title")})
    private WebElement title;

    @FindBys({@FindBy(className = "sbox-flights-container"), @FindBy(className = "sbox-search")})
    private WebElement searchButton;

    @FindBys({
        @FindBy(className = "sbox-flights-container"), @FindBy(className = "sbox-ui-form-select"),
        @FindBy(className = "sbox-children")})
    private List<WebElement> childrenAges;

    public FlightsHomePage(Configuration configuration, WebDriver driver, int timeout) {
        super(configuration, driver, timeout);
    }

    public String titleText() {
        return this.title.getText();
    }

    public String originPlaceHolder() {
        return this.origin.getAttribute(PLACEHOLDER);
    }

    public String destinationPlaceHolder() {
        return this.destination.getAttribute(PLACEHOLDER);
    }

    public String dateFromPlaceHolder() {
        return this.dateFrom.getAttribute(PLACEHOLDER);
    }

    public String dateToPlaceHolder() {
        return this.dateTo.getAttribute(PLACEHOLDER);
    }

    public FlightsSearchResultPage search(FlightsSearchGuiData flightsSearchGuiData) {

        this.selectOrigin(flightsSearchGuiData);
        this.selectDestination(flightsSearchGuiData);
        this.selectAdults(flightsSearchGuiData);
        this.selectChildren(flightsSearchGuiData);
        this.selectDates(flightsSearchGuiData);
        this.selectOneWay(flightsSearchGuiData);

        this.searchButton.click();

        return new FlightsSearchResultPage(this.configuration, this.driver, this.timeout);
    }


    private void selectOneWay(FlightsSearchGuiData flightsSearchGuiData) {
        if (flightsSearchGuiData.getOneWay()) {
            this.flightsGoing.click();
        }
    }

    private void selectDestination(FlightsSearchGuiData flightsSearchGuiData) {
        this.destination.sendKeys(flightsSearchGuiData.getDestination());

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(new ByChained(By.className("sbox-flights-container"),
            By.className("destination-container"), By.className("sbox-ui-autocomplete-list"), By.className("item"))));
        this.destination.sendKeys(Keys.ENTER);
    }

    private void selectOrigin(FlightsSearchGuiData flightsSearchGuiData) {
        this.origin.sendKeys(flightsSearchGuiData.getOrigin());
        this.wait.until(ExpectedConditions
            .visibilityOfElementLocated(new ByChained(By.className("sbox-ui-autocomplete-list"), By.className("item"))));
        this.origin.sendKeys(Keys.ENTER);

    }

    private void selectChildren(FlightsSearchGuiData flightsSearchGuiData) {
        this.children.sendKeys(flightsSearchGuiData.getNumberOfChildren());

        for (int i = 0; i < this.childrenAges.size(); i++) {

            Select childrenAgeCombo = new Select(this.childrenAges.get(i));
            childrenAgeCombo.selectByValue(flightsSearchGuiData.getChildrenAges().get(i));
        }
    }

    private void selectAdults(FlightsSearchGuiData flightsSearchGuiData) {
        this.adults.sendKeys(flightsSearchGuiData.getNumberOfAdults());
    }

    private void selectDates(FlightsSearchGuiData flightsSearchGuiData) {
        DateTime dateFromSearch = new DateTime(flightsSearchGuiData.getDateFrom());
        DateTime dateToSearch = new DateTime(flightsSearchGuiData.getDateTo());
        int actualMonth = (new DateTime()).getMonthOfYear();
        String dayFrom = String.valueOf(dateFromSearch.getDayOfMonth());
        String dayTo = String.valueOf(dateToSearch.getDayOfMonth());

        int dateFromPanel = this.getDatePanelIndex(actualMonth, dateFromSearch);
        int dateToPanel = this.getDatePanelIndex(dateFromSearch.getMonthOfYear(), dateToSearch);

        this.dateFrom.click();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sbox-ui-datepicker")));

        this.driver.findElements(By.linkText(dayFrom)).get(dateFromPanel).click();
        this.dateTo.click();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sbox-ui-datepicker")));
        this.driver.findElements(By.linkText(dayTo)).get(dateToPanel).click();
    }

    private int getDatePanelIndex(int month, DateTime date) {
        return date.getMonthOfYear() - month;
    }

    private String getUrl() {
        return this.baseUrl + this.configuration.getOfficeData().getKeys().get("text.word.flights") + "/";
    }

    public void go() {
        this.driver.get(this.getUrl());

        this.waitComponentsPage();

        // Close popup
        try {
            if (this.popup.isDisplayed()) {
                this.closePopupButton.click();
            }
        } catch (Exception e) {
            LOGGER.warn("There is no pop up", e);
        }


    }

    public void waitComponentsPage() {
        List<WebElement> elements = new ArrayList<WebElement>();
        elements.add(this.searchButton);
        elements.add(this.destination);
        elements.add(this.origin);
        elements.add(this.dateFrom);
        elements.add(this.dateTo);
        elements.add(this.title);
        elements.add(this.adults);
        elements.add(this.children);
        elements.add(this.flightsGoing);
        this.wait.until(ExpectedConditions.visibilityOfAllElements(elements));

    }

}
