package tp.databases.builderconnectdatabase;

import java.text.MessageFormat;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.despegar.automation.commons.config.model.Configuration;

public class BuilderConnectDataBase {

    private BuilderConnectDataBase() {
        super();
    }

    public static JdbcTemplate buildCruiseSite(Configuration configuration, CruiseSiteDatabase dbName) {
        Map<String, String> keys = configuration.getEnvironment().getKeys();
        String url = MessageFormat.format(keys.get("cruiseSite_dataSource_url"), keys.get("cruiseSite_dataSource_host"),
            keys.get("cruiseSite_dataSource_port"), dbName.getCode());

        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, keys.get("cruiseSite_dataSource_username"),
            keys.get("cruiseSite_dataSource_password"));
        dataSource.setDriverClassName(keys.get("cruiseSite_dataSource_driverClassName"));

        return new JdbcTemplate(dataSource);
    }

}
