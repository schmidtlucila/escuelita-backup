package tp.flights.site;

import java.net.URI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.despegar.automation.commons.config.contextappmanager.page.AbstractPage;
import com.despegar.automation.commons.config.model.Configuration;
import com.despegar.automation.commons.config.selenium.SeleniumManager;

public class FlightsSitePage
    extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightsSitePage.class.getName());

    protected WebDriver driver;
    protected SeleniumManager seleniumManager;
    protected Integer timeout;
    protected WebDriverWait wait;

    public FlightsSitePage(Configuration configuration, WebDriver driver, Integer timeout) {
        super(configuration);
        this.driver = driver;
        this.driver.manage().window().maximize();
        this.seleniumManager = new SeleniumManager();
        PageFactory.initElements(this.driver, this);
        this.timeout = timeout;
        this.wait = new WebDriverWait(this.driver, this.timeout);
    }

    @Override
    protected String builderBaseUrl() {
        String officeCode = this.configuration.getOfficeData().getName();
        String key = officeCode + "_gui_flightsHome";
        return this.configuration.getEnvironment().getKeys().get(key);
    }

    protected String getFlightsSiteDomainName(String url) {
        String domain = null;
        try {
            URI uri = new URI(url);
            domain = uri.getHost().replace("www.", "");
            return domain;
        } catch (Exception e) {
            LOGGER.error("Error while getting flights domain: ", e);
        }
        return domain;
    }

}
