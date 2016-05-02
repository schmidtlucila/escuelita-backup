package tp.config;

import java.io.File;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.despegar.automation.commons.config.model.Environment;
import com.despegar.automation.commons.config.model.ExecutionContextApp;
import com.despegar.automation.commons.config.model.OfficeData;

public class AutomationConfigTest {

    private static Logger LOGGER = LoggerFactory.getLogger(AutomationConfigTest.class.getName());

    @Test
    public void showConfigFlights() {
        LOGGER.info("------------------------------showConfigFlights------------------------------");
        ExecutionContextAppManager executionContextAppManager = new ExecutionContextAppManager(ApplicationCode.FLIGHTS);
        ExecutionContextApp executionContextApp = executionContextAppManager.getExecutionContext();

        Map<String, Environment> environments = executionContextApp.getEnvironments();

        Map<String, OfficeData> officesData = executionContextApp.getOfficesData();

        Map<String, File> testsData = executionContextApp.getTestsData();

        LOGGER.info("ALL ENVIRONMENTS: " + environments.toString());
        LOGGER.info("ALL OFFICES DATA: " + officesData.toString());
        LOGGER.info("ALL TESTS DATA: " + testsData.toString());
    }


    @Test
    public void showConfigCRUISE() {
        LOGGER.info("------------------------------showConfigCRUISE------------------------------");
        ExecutionContextAppManager executionContextAppManager = new ExecutionContextAppManager(ApplicationCode.CRUISE);
        ExecutionContextApp executionContextApp = executionContextAppManager.getExecutionContext();

        Map<String, Environment> environments = executionContextApp.getEnvironments();

        Map<String, OfficeData> officesData = executionContextApp.getOfficesData();

        Map<String, File> testsData = executionContextApp.getTestsData();

        LOGGER.info("ALL ENVIRONMENTS: " + environments.toString());
        LOGGER.info("ALL OFFICES DATA: " + officesData.toString());
        LOGGER.info("ALL TESTS DATA: " + testsData.toString());
    }

}
