package tp.services.flights.fsm.searchmethods.request;

import org.joda.time.DateTime;

public class FlightsSearchFullRequest {

    private String searchType;
    private String country;
    private int brand;
    private int product;
    private int adults;
    private int children;
    private int infants;
    private String itemType;
    private String from;
    private String to;
    private DateTime departureDate;
    private DateTime returnDate;
    private String fareSelector;
    private String channel;

    public String getSearchType() {
        return this.searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getBrand() {
        return this.brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public int getProduct() {
        return this.product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getAdults() {
        return this.adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return this.children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getInfants() {
        return this.infants;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }

    public String getItemType() {
        return this.itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public DateTime getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(DateTime departureDate) {
        this.departureDate = departureDate;
    }

    public DateTime getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(DateTime returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "FlightsSearchFullRequest [searchType=" + this.searchType + ", country=" + this.country + ", brand="
            + this.brand + ", product=" + this.product + ", adults=" + this.adults + ", children=" + this.children
            + ", infants=" + this.infants + ", itemType=" + this.itemType + ", from=" + this.from + ", to=" + this.to
            + ", departureDate=" + this.departureDate + ", returnDate=" + this.returnDate + "]";
    }

    public String getFareSelector() {
        return this.fareSelector;
    }

    public void setFareSelector(String fareSelector) {
        this.fareSelector = fareSelector;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}


