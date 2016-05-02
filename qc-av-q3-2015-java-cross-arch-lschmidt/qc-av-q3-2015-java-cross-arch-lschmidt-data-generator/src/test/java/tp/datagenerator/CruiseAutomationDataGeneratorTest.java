package tp.datagenerator;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.datagenerator.cruise.GeneratorCruise;
import tp.datagenerator.model.OfficeCompanyData;

public class CruiseAutomationDataGeneratorTest {
    private static Logger LOGGER = LoggerFactory.getLogger(CruiseAutomationDataGeneratorTest.class.getName());

    @Test
    public void testDataGenerator() {
        List<OfficeCompanyData> dataList = GeneratorCruise.getOfficeCompanyData();
        for (OfficeCompanyData officeCompanyData : dataList) {
            LOGGER.info(officeCompanyData.toString());
        }
        LOGGER.info("Combination Size: " + dataList.size());
    }
}
