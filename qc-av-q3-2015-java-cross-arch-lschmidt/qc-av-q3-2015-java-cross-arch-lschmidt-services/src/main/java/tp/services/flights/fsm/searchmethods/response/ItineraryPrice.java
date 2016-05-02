package tp.services.flights.fsm.searchmethods.response;

import java.util.List;

public class ItineraryPrice {



    private List<PriceDetail> all;

    public List<PriceDetail> getAll() {
        return this.all;
    }

    public void setAll(List<PriceDetail> all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "ItineraryPrice [all=" + this.all + "]";
    }


}
