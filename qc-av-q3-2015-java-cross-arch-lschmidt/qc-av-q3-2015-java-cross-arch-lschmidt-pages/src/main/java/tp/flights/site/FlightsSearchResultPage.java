package tp.flights.site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.despegar.automation.commons.config.model.Configuration;

public class FlightsSearchResultPage
    extends FlightsSitePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightsSearchResultPage.class);
    private static final String POP_UP_CLASS = "nibbler-common-overlay-background";
    private static final String SUGGESTION_POP_UP_CLASS = "user-suggestion-alert-popup";
    private static final String TOP_POP_UP_CLASS = "flights-popup-top";
    private static final String SEARCHES_COUNT_POP_UP_ID = "searches-count-alert-popup";

    private SearchResult firstResult;

    public FlightsSearchResultPage(Configuration configuration, WebDriver driver, Integer timeout) {
        super(configuration, driver, timeout);
        this.waitComponentsPage();
    }

    public void selectFirstOptionsOfResult() {
        this.firstResult.selectFirstOutBoundChoice();
        this.firstResult.selectFirstInBoundChoice();
    }

    public FlightsCheckoutPage clickBuyButtonFirstResult() {
        this.closeSuggestionPopUp();
        this.tryCloseSearchCountPopUp();
        this.tryClosePriceAlertPopUp();
        this.tryCloseTopPopUp();
        this.firstResult.getBuyButton().click();
        return new FlightsCheckoutPage(this.configuration, this.driver, this.timeout);
    }

    private void tryCloseTopPopUp() {
        LOGGER.info("Top pop up is going to be closed if present.");
        try {
            WebElement popUp = this.driver.findElement(new ByChained(By.className(TOP_POP_UP_CLASS)));
            if (popUp.isDisplayed()) {
                WebElement closeButton = this.driver
                    .findElement(new ByChained(By.className(TOP_POP_UP_CLASS), By.className("popup-close-button")));
                closeButton.click();
            }
        } catch (Exception e) {
            LOGGER.info("Top pop up was not there", e);
        }

    }

    private void tryCloseSearchCountPopUp() {
        LOGGER.info("Search count pop up is going to be closed if present.");
        try {
            (new WebDriverWait(this.driver, 30)).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return driver.findElement(By.id(SEARCHES_COUNT_POP_UP_ID)).isDisplayed();
                }
            });
            if (this.driver.findElement(By.id(SEARCHES_COUNT_POP_UP_ID)).isDisplayed()) {
                this.driver.findElement(By.id(SEARCHES_COUNT_POP_UP_ID)).findElement(By.className("popup-close-button"))
                    .click();
            }
            LOGGER.info("No searches count pop up");

        } catch (Exception ex) {
            LOGGER.info("Searches count pop up was not found", ex);
        }

    }

    private void tryClosePriceAlertPopUp() {
        LOGGER.info("Price alert pop up is going to be closed if present.");
        try {
            (new WebDriverWait(this.driver, 30)).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {

                    return driver.findElement(By.className(POP_UP_CLASS)).isDisplayed();
                }
            });
            if (this.driver.findElement(By.className(POP_UP_CLASS)).isDisplayed()) {
                this.driver.findElement(By.className(POP_UP_CLASS)).findElement(By.className("nibbler-common-overlay-close"))
                    .click();
            }

        } catch (Exception ex) {
            LOGGER.info("No se encontro cartel grande de registro", ex);
        }
    }

    private void closeSuggestionPopUp() {
        LOGGER.info("Suggestion pop up is going to be closed if present.");
        try {
            (new WebDriverWait(this.driver, 30)).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return driver.findElement(By.id(SUGGESTION_POP_UP_CLASS)).isDisplayed();
                }
            });
            if (this.driver.findElement(By.id(SUGGESTION_POP_UP_CLASS)).isDisplayed()) {
                this.driver.findElement(By.id(SUGGESTION_POP_UP_CLASS)).findElement(By.className("user-suggestion-close"))
                    .click();
            }
        } catch (Exception ex) {
            LOGGER.info("Suggestion pop up was not found", ex);
        }
    }

    private void waitComponentsPage() {
        this.wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.findElement(By.className("matrix-container")).isDisplayed()
                    && d.findElement(By.className("fare-detail")).isDisplayed();
            }
        });

    }

    public void collectResultsInformation() {
        WebElement cluster = this.driver.findElement(By.className("cluster"));
        this.setFirstResult(new SearchResult(cluster, this.configuration));
    }

    public SearchResult getFirstResult() {
        return this.firstResult;
    }

    public void setFirstResult(SearchResult firstResult) {
        this.firstResult = firstResult;
    }

}
