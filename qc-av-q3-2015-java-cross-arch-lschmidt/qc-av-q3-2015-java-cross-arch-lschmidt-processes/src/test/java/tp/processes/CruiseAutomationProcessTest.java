package tp.processes;

import org.junit.Test;

import tp.processes.reportcollect.ReportCollect;

public class CruiseAutomationProcessTest {

    @Test
    public void test() {
        String environment = "ic";
        String timeCountry = "AR";

        ReportCollect reportCollect = new ReportCollect();
        reportCollect.generate(timeCountry, environment);
    }


}
