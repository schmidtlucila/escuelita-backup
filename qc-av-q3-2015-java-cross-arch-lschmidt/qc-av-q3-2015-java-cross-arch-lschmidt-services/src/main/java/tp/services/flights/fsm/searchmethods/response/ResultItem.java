package tp.services.flights.fsm.searchmethods.response;

public class ResultItem {

    private String id;
    private Itinerary itinerary;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Itinerary getItinerary() {
        return this.itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    @Override
    public String toString() {
        return "ResultItem [id=" + this.id + ", itinerary=" + this.itinerary + "]";
    }



}
