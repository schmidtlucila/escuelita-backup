package tp.flights.model;

import java.util.List;

public class FlightsSearchGuiData {

    private String origin;
    private String destination;
    private String dateFrom;
    private String dateTo;
    private String numberOfAdults;
    private String numberOfChildren;
    private List<String> childrenAges;
    private boolean oneWay;



    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateFrom() {
        return this.dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return this.dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getNumberOfAdults() {
        return this.numberOfAdults;
    }

    public void setNumberOfAdults(String numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public String getNumberOfChildren() {
        return this.numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public String toString() {
        return "FlightsSearchGuiData [origin=" + this.origin + ", destination=" + this.destination + ", dateFrom="
            + this.dateFrom + ", dateTo=" + this.dateTo + ", numberOfAdults=" + this.numberOfAdults + ", numberOfChildren="
            + this.numberOfChildren + "]";
    }

    public List<String> getChildrenAges() {
        return this.childrenAges;
    }

    public void setChildrenAges(List<String> childrenAges) {
        this.childrenAges = childrenAges;
    }

    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;

    }

    public boolean getOneWay() {
        return this.oneWay;

    }


}
