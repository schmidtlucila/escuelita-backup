package tp.services.flights;

import java.util.Map;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.despegar.automation.commons.config.contextappmanager.service.AbstractService;
import com.despegar.automation.commons.config.model.Configuration;

import tp.config.ProjectNameHeaders;
import tp.config.ProjectXuowValues;

public abstract class DespegarService
    extends AbstractService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DespegarService.class.getName());
    protected Header xUowHeader;
    protected Header xClientHeader;
    protected Header contentTypeJson;
    protected Header contentTypeUrlEncoded;
    protected Header xVersionOverride;

    public DespegarService(Configuration configuration) {
        super(configuration);
        Map<String, String> keysEnvironments = configuration.getEnvironment().getKeys();

        this.xUowHeader = new BasicHeader(ProjectNameHeaders.PROJECT_AUTOMATION_SERVICES_XUOW.getCode(),
            ProjectXuowValues.PROJECT_AUTOMATION_SERVICES.getCode() + "-" + UUID.randomUUID());
        this.xClientHeader = new BasicHeader("X-Client", "");
        this.contentTypeJson = new BasicHeader("Content-type", "application/json");
        this.contentTypeUrlEncoded = new BasicHeader("Content-type", "application/x-www-form-urlencoded");
        this.xVersionOverride = new BasicHeader(ProjectNameHeaders.X_VERSION_OVERRIDE.getCode(),
            keysEnvironments.get("x_version_override"));

    }

    protected HttpRequestBase addHeadersByDefault(HttpRequestBase httpRequest) {
        httpRequest.setHeader(this.contentTypeJson);
        httpRequest.setHeader(this.xClientHeader);
        httpRequest.setHeader(this.xUowHeader);
        httpRequest.setHeader(this.xVersionOverride);
        return httpRequest;
    }

    protected void loggerHeaders(Header[] headers) {
        String headersValues = "";
        for (Header header : headers) {
            headersValues += "|" + header.toString();
        }
        LOGGER.info("Headers: " + headersValues.toString());
    }

    @Override
    protected abstract String builderEndpoint();
}


