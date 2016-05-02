package tp.services.flights.fsm.searchmethods.response;

public class Segment {

    @Override
    public String toString() {
        return "Segment [departure=" + this.departure + ", arrival=" + this.arrival + "]";
    }

    private AirportEvent departure;
    private AirportEvent arrival;

    public AirportEvent getDeparture() {
        return this.departure;
    }

    public void setDeparture(AirportEvent departure) {
        this.departure = departure;
    }

    public AirportEvent getArrival() {
        return this.arrival;
    }

    public void setArrival(AirportEvent arrival) {
        this.arrival = arrival;
    }


}
