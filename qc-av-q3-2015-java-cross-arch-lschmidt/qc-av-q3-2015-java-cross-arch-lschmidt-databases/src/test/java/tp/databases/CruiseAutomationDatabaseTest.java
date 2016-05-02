package tp.databases;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.despegar.automation.commons.config.model.Configuration;
import com.despegar.automation.commons.config.model.Environment;
import com.despegar.automation.commons.config.model.ExecutionContextApp;
import com.despegar.automation.commons.config.model.OfficeData;
import tp.config.ApplicationCode;
import tp.config.ExecutionContextAppManager;
import tp.databases.jdbctemplates.CruiseDataJdbcTemplate;
import tp.databases.model.CruiseData;


public class CruiseAutomationDatabaseTest {
    private static Logger LOGGER = LoggerFactory.getLogger(CruiseAutomationDatabaseTest.class.getName());

    @Test
    public void test() {
        ExecutionContextAppManager executionContextAppManager = new ExecutionContextAppManager(
            ApplicationCode.CRUISE);
        ExecutionContextApp executionContextApp = executionContextAppManager.getExecutionContext();
        Environment environment = executionContextApp.getEnvironments().get("rc");
        OfficeData officeData = executionContextApp.getOfficesData().get("cruise_despegar_ar");

        Configuration config = new Configuration(environment, officeData);
        config.setEnvironment(environment);
        config.setOfficeData(officeData);
        
        CruiseDataJdbcTemplate jdbcTemplate = new CruiseDataJdbcTemplate(config);
        List<CruiseData> listResult = jdbcTemplate.getCruisesData("-3");
        Assert.assertFalse(listResult.isEmpty());
        for (CruiseData cruiseData : listResult) {
            LOGGER.info(cruiseData.toString());
        }
    }
}
