package tp.flights.site;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import com.despegar.automation.commons.config.model.Configuration;

public class SearchResult {

    private static final String FARE_CONTAINER_CLASS = "fare-container";
    private static final String BUTTON_CLASS = "btn-buy-red";
    private Configuration configuration;
    private WebElement cluster;

    private int numberOfAdults;
    private int numberOfBabies;
    private int numberOfChildren;
    private int charges;
    private int taxes;
    private int afip;
    private int priceOfBabies;
    private int priceOfAdults;
    private int priceOfChildren;
    private int totalPrice;
    private int fare;

    public int getFare() {
        return this.fare;
    }

    public SearchResult(WebElement cluster, Configuration configuration) {
        this.configuration = configuration;
        this.cluster = cluster;
        this.initialize();
    }

    private void initialize() {
        if ("flights_despegar_ar".equals(this.configuration.getOfficeData().getName())) {
            this.initializeDetailsAndPrices();
        }

        this.initializeFare();
    }


    private void initializeFare() {
        String currencyCode = this.configuration.getOfficeData().getKeys().get("currency.code");
        String fareText = this.cluster
            .findElement(new ByChained(By.className("fare"), By.className(currencyCode), By.className("price-amount")))
            .getText();
        this.fare = this.getAmountFrom(fareText);
    }


    @Override
    public String toString() {
        return "Cluster [cluster=" + this.cluster + ", numberOfAdults=" + this.numberOfAdults + ", numberOfBabies="
            + this.numberOfBabies + ", numberOfChildren=" + this.numberOfChildren + ", charges=" + this.charges + ", taxes="
            + this.taxes + ", afip=" + this.afip + ", priceOfBabies=" + this.priceOfBabies + ", priceOfAdults="
            + this.priceOfAdults + ", priceOfChildren=" + this.priceOfChildren + ", totalPrice=" + this.totalPrice + "]";
    }

    private String babyWord() {
        return this.configuration.getOfficeData().getKeys().get("baby.word");
    }

    private String childWord() {
        return this.configuration.getOfficeData().getKeys().get("child.word");
    }

    private String adultWord() {
        return this.configuration.getOfficeData().getKeys().get("adult.word");
    }

    private Integer getNumberAtBeginning(String text) {
        return Integer.valueOf(text.split(" ")[0]);
    }

    private void initializeDetailsAndPrices() {
        String currencyCode = this.configuration.getOfficeData().getKeys().get("currency.code");
        List<WebElement> details = this.cluster
            .findElements(new ByChained(By.className("fare-detail-items"), By.className("item-detail")));
        List<WebElement> prices = this.cluster.findElements(
            new ByChained(By.className("fare-detail-items"), By.className("item-price"), By.className(currencyCode)));

        String detailString;
        String priceString;

        for (int i = 0; i < details.size(); i++) {
            detailString = details.get(i).getText();
            priceString = prices.get(i).getText();

            this.initializeBabiesIfNecessary(detailString, priceString);
            this.initializeChildrenIfNecessary(detailString, priceString);
            this.initializeAdultsIfNecessary(detailString, priceString);
            this.initializeTaxesIfNecessary(detailString, priceString);
            this.initializeAfipIfNecessary(detailString, priceString);
            this.initializeChargesIfNecessary(detailString, priceString);
            this.initializeTotalIfNecessary(detailString, priceString);
        }
    }

    private void initializeTotalIfNecessary(String detailString, String priceString) {
        if (detailString.contains(this.totalWord())) {
            this.totalPrice = this.getAmountFrom(priceString);
        }
    }

    private void initializeAfipIfNecessary(String detailString, String priceString) {
        if (detailString.contains(this.afipWord())) {
            this.afip = this.getAmountFrom(priceString);
        }
    }

    private void initializeTaxesIfNecessary(String detailString, String priceString) {
        if (detailString.contains(this.taxesWord())) {
            this.taxes = this.getAmountFrom(priceString);
        }
    }

    private void initializeChargesIfNecessary(String detailString, String priceString) {
        if (detailString.contains(this.chargesWord())) {
            this.charges = this.getAmountFrom(priceString);
        }
    }

    private String totalWord() {
        return this.configuration.getOfficeData().getKeys().get("total.word");
    }

    private String taxesWord() {
        return this.configuration.getOfficeData().getKeys().get("taxes.word");
    }

    private String afipWord() {
        return this.configuration.getOfficeData().getKeys().get("afip.word");
    }

    private String chargesWord() {
        return this.configuration.getOfficeData().getKeys().get("charges.word");
    }

    private void initializeAdultsIfNecessary(String detailString, String priceString) {
        if (detailString.contains(this.adultWord())) {
            this.numberOfAdults = this.getNumberAtBeginning(detailString);
            this.priceOfAdults = this.getAmountFrom(priceString);
        }

    }

    private void initializeChildrenIfNecessary(String detailString, String priceString) {
        if (detailString.contains(this.childWord())) {
            this.numberOfChildren = this.getNumberAtBeginning(detailString);
            this.priceOfChildren = this.getAmountFrom(priceString);
        }
    }

    private void initializeBabiesIfNecessary(String detailString, String priceString) {
        if (detailString.contains(this.babyWord())) {
            this.numberOfBabies = this.getNumberAtBeginning(detailString);
            this.priceOfBabies = this.getAmountFrom(priceString);
        }
    }


    private int getAmountFrom(String stringNumber) {
        return Integer.valueOf(stringNumber.replace(" ", "").replace("$", "").replace(".", ""));
    }

    public int getNumberOfAdults() {
        return this.numberOfAdults;
    }

    public int getNumberOfBabies() {
        return this.numberOfBabies;
    }

    public int getNumberOfChildren() {
        return this.numberOfChildren;
    }

    public int getCharges() {
        return this.charges;
    }

    public int getTaxes() {
        return this.taxes;
    }

    public int getAfip() {
        return this.afip;
    }

    public int getTotalPrice() {
        return this.totalPrice;
    }

    public int getPriceOfAdults() {
        return this.priceOfAdults;
    }

    public int getPriceOfChildren() {
        return this.priceOfChildren;
    }

    public int getPriceOfBabies() {
        return this.priceOfBabies;
    }

    public WebElement getBuyButton() {
        List<WebElement> buttons = this.getAllBuyButtons();
        for (WebElement button : buttons) {
            if (button.isDisplayed()) {
                return button;
            }
        }

        throw new SearchResultException("No buy button is displayed");
    }

    private List<WebElement> getAllBuyButtons() {
        return this.cluster.findElements(new ByChained(By.className(FARE_CONTAINER_CLASS), By.className(BUTTON_CLASS)));
    }

    public void selectFirstOutBoundChoice() {
        this.cluster.findElement(new ByChained(By.className("outbound"), By.className("radio"))).click();
    }

    public void selectFirstInBoundChoice() {
        this.cluster.findElement(new ByChained(By.className("inbound"), By.className("radio"))).click();
    }



}
