package tp.services.flights.fsm.searchmethods;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.despegar.automation.commons.config.model.Configuration;
import com.despegar.automation.commons.utils.JacksonObjectUtils;

import tp.services.flights.fsm.FSMService;
import tp.services.flights.fsm.searchmethods.request.FlightsSearchFullRequest;
import tp.services.flights.fsm.searchmethods.response.FlightsSearchFullResponse;

public class FlightsSearchFull
    extends FSMService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightsSearchFull.class.getName());
    private static final String PATH = "fsm-service/service/items";
    private static final String SEARCH_TYPE = "searchtype";
    private static final String COUNTRY = "country";
    private static final String FROM = "from";
    private static final String TO = "to";
    private static final String FARE_SELECTOR = "matrixfareselectortype";
    private static final String ADULTS = "adults";
    private static final String CHILDREN = "childs";
    private static final String RETURN_DATE = "return";
    private static final String DEPARTURE_DATE = "departure";
    private static final String ITEM_TYPE = "itemtype";
    private static final String BRAND = "brand";
    private static final String PRODUCT = "product";
    private static final String CHANNEL = "channel";

    private String pattern = "yyyy-MM-dd";
    private DateTimeFormatter formatter = DateTimeFormat.forPattern(this.pattern);


    public FlightsSearchFull(Configuration configuration) {
        super(configuration);
    }

    public FlightsSearchFullResponse search(FlightsSearchFullRequest searchFullRequest) {
        try {
            String requestUrl = this.generateUrl(searchFullRequest);

            LOGGER.info("Request to Url: " + requestUrl);

            HttpGet httpRequest = new HttpGet(requestUrl);
            httpRequest = (HttpGet) this.addHeadersByDefault(httpRequest);
            this.loggerHeaders(httpRequest.getAllHeaders());

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpResponse response = httpclient.execute(httpRequest);

            String json = EntityUtils.toString(response.getEntity());

            LOGGER.info("Status code: " + response.getStatusLine().getStatusCode());

            return (FlightsSearchFullResponse) JacksonObjectUtils.convertStringToJson(json, FlightsSearchFullResponse.class);
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
        }
        return null;
    }

    private String generateUrl(FlightsSearchFullRequest searchFullRequest) {
        String url = this.endpoint + PATH;
        url = this.addSearchType(url, searchFullRequest.getSearchType());
        url = this.addCountry(url, searchFullRequest.getCountry());
        url = this.addBrand(url, searchFullRequest.getBrand());
        url = this.addProduct(url, searchFullRequest.getProduct());
        url = this.addAdults(url, searchFullRequest.getAdults());
        url = this.addChildren(url, searchFullRequest.getChildren());
        url = this.addItemType(url, searchFullRequest.getItemType());
        url = this.addFrom(url, searchFullRequest.getFrom());
        url = this.addTo(url, searchFullRequest.getTo());
        url = this.addDepartureDate(url, searchFullRequest.getDepartureDate());
        if ("roundtrip".equals(searchFullRequest.getSearchType())) {
            url = this.addReturnDate(url, searchFullRequest.getReturnDate());
        }
        url = this.addFareSelector(url, searchFullRequest.getFareSelector());
        url = this.addChannel(url, searchFullRequest.getChannel());
        LOGGER.info(url);

        return url;
    }

    private String addReturnDate(String url, DateTime returnDate) {
        return url + "&" + RETURN_DATE + "=" + this.formatter.print(returnDate);
    }


    private String addDepartureDate(String url, DateTime departureDate) {
        return url + "&" + DEPARTURE_DATE + "=" + this.formatter.print(departureDate);
    }

    private String addChannel(String url, String channel) {
        return url + "&" + CHANNEL + "=" + channel;
    }

    private String addFareSelector(String url, String fareSelector) {
        return url + "&" + FARE_SELECTOR + "=" + fareSelector;
    }

    private String addTo(String url, String to) {
        return url + "&" + TO + "=" + to;
    }

    private String addFrom(String url, String from) {
        return url + "&" + FROM + "=" + from;
    }

    private String addItemType(String url, String itemType) {
        return url + "&" + ITEM_TYPE + "=" + itemType;
    }

    private String addChildren(String url, int children) {
        return url + "&" + CHILDREN + "=" + children;
    }

    private String addAdults(String url, int adults) {
        return url + "&" + ADULTS + "=" + adults;
    }

    private String addProduct(String url, int product) {
        return url + "&" + PRODUCT + "=" + product;
    }

    private String addBrand(String url, int brand) {
        return url + "&" + BRAND + "=" + brand;
    }

    private String addCountry(String url, String country) {
        return url + "&" + COUNTRY + "=" + country;
    }

    private String addSearchType(String url, String searchType) {
        return url + "?" + SEARCH_TYPE + "=" + searchType;
    }

}
