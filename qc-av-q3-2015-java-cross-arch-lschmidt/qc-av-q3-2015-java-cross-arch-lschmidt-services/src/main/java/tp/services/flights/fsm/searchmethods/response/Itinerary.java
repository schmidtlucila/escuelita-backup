package tp.services.flights.fsm.searchmethods.response;

import java.util.List;

public class Itinerary {

    private List<Route> routes;
    private ItineraryPrice itineraryPrice;

    @Override
    public String toString() {
        return "Itinerary [routes=" + this.routes + ", iteneraryPrice=" + this.getItineraryPrice() + "]";
    }

    public List<Route> getRoutes() {
        return this.routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public ItineraryPrice getItineraryPrice() {
        return this.itineraryPrice;
    }

    public void setItineraryPrice(ItineraryPrice itineraryPrice) {
        this.itineraryPrice = itineraryPrice;
    }



}
