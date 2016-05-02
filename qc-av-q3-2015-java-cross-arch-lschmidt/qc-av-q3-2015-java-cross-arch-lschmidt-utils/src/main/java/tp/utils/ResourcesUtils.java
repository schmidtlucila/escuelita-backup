package tp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.despegar.automation.commons.utils.QCFileUtils;

public class ResourcesUtils {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesUtils.class.getName());

    private ResourcesUtils() {

    }

    public static InputStream getResourceAsInputStream(String fullPathFile) {
        return ResourcesUtils.class.getResourceAsStream(fullPathFile);
    }

    public static Properties getResourceAsProperties(String fullPathFile) {
        InputStream inputStream = ResourcesUtils.getResourceAsInputStream(fullPathFile);
        return QCFileUtils.loadProperties(inputStream, "ISO-8859-1");
    }

    public static String getResourceAsString(String fullPathFile) {
        try {
            InputStream inputStream = ResourcesUtils.getResourceAsInputStream(fullPathFile);
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "ISO-8859-1");
            return writer.toString();
        } catch (IOException e) {
            LOGGER.error("Error while reading resources: ", e);
        }
        return null;
    }

}
