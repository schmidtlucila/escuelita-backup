package tp.tests.impl.testng.configuration;

import java.net.InetAddress;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.despegar.automation.commons.config.model.Configuration;
import com.despegar.automation.commons.config.model.Environment;
import com.despegar.automation.commons.config.model.ExecutionContextApp;
import com.despegar.automation.commons.config.model.OfficeData;
import com.despegar.automation.commons.config.selenium.SeleniumManager;

import tp.config.ApplicationCode;
import tp.config.ExecutionContextAppManager;

public class ConfigurationLoaderGui {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationLoaderGui.class.getName());
    protected String browser;
    protected ExecutionContextApp executionContextApp;
    protected Configuration configuration;
    protected Environment secondEnvironment;
    protected WebDriver driver;
    protected SeleniumManager seleniumManager;

    @Parameters({"environment", "officeData", "browser", "secondEnvironment"})
    @BeforeClass
    public void buildConfiguration(String environmentValue, String officeDataValue, String browser,
        String secondEnvironmentValue) {
        try {
            ExecutionContextAppManager executionContextAppManager = new ExecutionContextAppManager(ApplicationCode.FLIGHTS);
            this.executionContextApp = executionContextAppManager.getExecutionContext();
            Environment environment = this.executionContextApp.getEnvironments().get(environmentValue);
            OfficeData officeData = this.executionContextApp.getOfficesData().get(officeDataValue);
            this.secondEnvironment = this.executionContextApp.getEnvironments().get(secondEnvironmentValue);
            this.configuration = new Configuration(environment, officeData);
            this.browser = browser;
            this.seleniumManager = new SeleniumManager();

            LOGGER.info("Host IP: " + InetAddress.getLocalHost());
        } catch (Exception e) {
            LOGGER.error("Error while buildConfiguration: ", e);
        }
    }

    @BeforeMethod
    public void initDriver() {
        try {
            String chromeDriverFileName = this.configuration.getEnvironment().getKeys().get("chrome_driver_filename");
            this.driver = this.seleniumManager.buildDriver(this.executionContextApp, this.browser, chromeDriverFileName);
        } catch (Exception e) {
            LOGGER.error("Error while initDriver: ", e);
        }
    }

    // @AfterMethod
    public void closeDriver(ITestResult result) {
        try {
            if (!result.isSuccess()) {
                String nameTest = result.getMethod().getMethodName() + "-" + UUID.randomUUID();
                this.seleniumManager.addScreenshot(this.driver, false, "output/", nameTest, "jpg");
            }
            this.seleniumManager.quitDriver(this.driver);
        } catch (Exception e) {
            LOGGER.error("Error while closeDriver: ", e);
        }
    }
}
