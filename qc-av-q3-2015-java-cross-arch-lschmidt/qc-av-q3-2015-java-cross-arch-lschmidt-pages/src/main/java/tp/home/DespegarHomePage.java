package tp.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.despegar.automation.commons.config.contextappmanager.page.AbstractPage;
import com.despegar.automation.commons.config.model.Configuration;
import com.despegar.automation.commons.config.selenium.SeleniumManager;

public class DespegarHomePage
    extends AbstractPage {

    protected WebDriver driver;
    protected SeleniumManager seleniumManager;
    protected Integer timeout;
    protected WebDriverWait wait;

    public DespegarHomePage(Configuration configuration, WebDriver driver) {
        super(configuration);
        this.driver = driver;
        this.seleniumManager = new SeleniumManager();
        PageFactory.initElements(this.driver, this);
        this.timeout = Integer.parseInt(this.configuration.getEnvironment().getKeys().get("cruise_despegar_site_timeout"));
        this.wait = new WebDriverWait(this.driver, this.timeout);
    }



    @Override
    protected String builderBaseUrl() {
        return null;
    }

}
