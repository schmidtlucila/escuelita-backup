package tp.processes.henry;

import java.util.Map;

import org.apache.log4j.Logger;

import tp.processes.reportcollect.ReportCollect;
import com.despegar.henry.container.entities.ResultCode;
import com.despegar.henry.container.entities.ResultInfo;
import com.despegar.henry.container.tasks.TestTask;

public class ExecutorCollectReport
    extends TestTask {

    @Override
    protected void doExecute(Map<String, Object> parameters) {
        // Get parameters of in.henry
        String environment = parameters.get("environment").toString();
        String timeCountry = parameters.get("timeCountry").toString();

        final Logger log = this.getLogger();
        log.info("---------- Henry Parameters ----------");
        log.info("Environment\t: " + environment);
        log.info("TimeCountry\t: " + timeCountry);
        log.info("--------------------------------------");

        try {
            ReportCollect reportCollect = new ReportCollect();
            reportCollect.generate(timeCountry, environment);
        } catch (Exception e) {
            log.error("Error while execute", e);
            this.registerActivity(e.getMessage(), ResultCode.ERROR);
        }
    }

    public void registerActivity(String message, ResultCode code) {
        ResultInfo ri = new ResultInfo();
        ri.setMessage(message);
        ri.setCode(code);
        this.registerResult(ri);
        this.setCode(ri.getCode());
    }
}
