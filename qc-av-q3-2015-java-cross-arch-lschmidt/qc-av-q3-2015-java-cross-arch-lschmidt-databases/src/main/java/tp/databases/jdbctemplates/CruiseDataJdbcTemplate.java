package tp.databases.jdbctemplates;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.despegar.automation.commons.config.model.Configuration;
import tp.databases.builderconnectdatabase.BuilderConnectDataBase;
import tp.databases.builderconnectdatabase.CruiseSiteDatabase;
import tp.databases.model.CruiseData;
import tp.utils.ResourcesUtils;

public class CruiseDataJdbcTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CruiseDataJdbcTemplate.class.getName());
    private final JdbcTemplate jdbcTemplate;

    public CruiseDataJdbcTemplate(Configuration configuration) {
        this.jdbcTemplate = BuilderConnectDataBase.buildCruiseSite(configuration, CruiseSiteDatabase.CRUISE_CHAS);
    }

    @SuppressWarnings("unchecked")
    public List<CruiseData> getCruisesData(String gmt) {
        String query = ResourcesUtils.getResourceAsString("/queries/cruiseDataGateway.sql");
        query = query.replace("{gmt}", gmt);
        LOGGER.info("--------------Test CruiseDataReservationRowMapper --------------\n" + query);
        return this.jdbcTemplate.query(query, new CruiseDataReservationRowMapper());
    }

    @SuppressWarnings("rawtypes")
    class CruiseDataReservationRowMapper
        implements RowMapper {

        public CruiseDataReservationRowMapper() {
        }

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            CruiseData cruiseData = new CruiseData();
            cruiseData.setDepartureDate(rs.getDate("departureDate"));
            cruiseData.setShipId(rs.getLong("shipId"));
            cruiseData.setDuration(rs.getInt("duration"));
            cruiseData.setItineraryId(rs.getLong("itineraryId"));
            cruiseData.setDeckId(rs.getLong("deckId"));
            cruiseData.setCabinCategoryId(rs.getLong("cabinCategory"));
            cruiseData.setBlockedCategoryId(rs.getLong("blockedCategoryId"));
            cruiseData.setCompanyContractId(rs.getLong("companyContractId"));
            cruiseData.setCompanyCode(rs.getString("companyCode"));
            cruiseData.setCabinCode(rs.getString("cabinCode"));
            return cruiseData;
        }
    }
}
