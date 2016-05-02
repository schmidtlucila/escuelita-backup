package tp.services.flights.fsm.searchmethods.response;

import java.util.List;

public class Route {

    private List<Segment> segments;

    public List<Segment> getSegments() {
        return this.segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    @Override
    public String toString() {
        return "Route [segments=" + this.segments + "]";
    }


}
