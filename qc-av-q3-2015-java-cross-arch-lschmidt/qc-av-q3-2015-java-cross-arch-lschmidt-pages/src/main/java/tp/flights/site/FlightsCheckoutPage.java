package tp.flights.site;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.despegar.automation.commons.config.model.Configuration;

public class FlightsCheckoutPage
    extends FlightsSitePage {


    @FindBys({@FindBy(id = "passengers"), @FindBy(className = "passenger")})
    private List<WebElement> passengerSections;

    private int amountOfChildSections = 0;
    private int amountOfAdultSections = 0;
    private int amountOfBabySections = 0;

    public FlightsCheckoutPage(Configuration configuration, WebDriver driver, Integer timeout) {
        super(configuration, driver, timeout);
        this.waitComponentsPage();
        this.initialize();
    }

    private void initialize() {
        for (WebElement section : this.passengerSections) {
            if (section.isDisplayed()) {
                String reference = section.findElement(By.className("reference")).getText();
                if (reference.contains(this.configuration.getOfficeData().getKeys().get("adult.word"))) {
                    this.amountOfAdultSections++;
                }
                if (reference.contains(this.configuration.getOfficeData().getKeys().get("child.word"))) {
                    this.amountOfChildSections++;
                }
                if (reference.contains(this.configuration.getOfficeData().getKeys().get("baby.word"))) {
                    this.amountOfBabySections++;
                }
            }


        }
    }

    public boolean allSectionHaveFirstAndLastNameFields() {

        for (WebElement section : this.passengerSections) {
            WebElement firstNameField = section.findElement(By.className("input-passenger-first-name"));
            WebElement lastNameField = section.findElement(By.className("input-passenger-last-name"));
            if (!firstNameField.isDisplayed() || !lastNameField.isDisplayed()) {
                return false;
            }
        }

        return true;
    }

    private void waitComponentsPage() {
        List<WebElement> elements = new ArrayList<WebElement>();
        elements.addAll(this.passengerSections);
        this.wait.until(ExpectedConditions.visibilityOfAllElements(elements));

    }

    public int getAmountOfBabySections() {
        return this.amountOfBabySections;
    }


    public int getAmountOfChildSections() {
        return this.amountOfChildSections;
    }

    public int getAmountOfAdultSections() {
        return this.amountOfAdultSections;
    }

}
