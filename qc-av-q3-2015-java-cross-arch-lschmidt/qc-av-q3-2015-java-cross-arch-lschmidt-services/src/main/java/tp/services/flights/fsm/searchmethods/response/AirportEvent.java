package tp.services.flights.fsm.searchmethods.response;

public class AirportEvent {

    @Override
    public String toString() {
        return "AirportEvent [location=" + this.location + ", locationDateTime=" + this.locationDateTime + "]";
    }

    private String location;
    private String locationDateTime;

    public String getLocationDateTime() {
        return this.locationDateTime;
    }

    public void setLocationDateTime(String locationDateTime) {
        this.locationDateTime = locationDateTime;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
