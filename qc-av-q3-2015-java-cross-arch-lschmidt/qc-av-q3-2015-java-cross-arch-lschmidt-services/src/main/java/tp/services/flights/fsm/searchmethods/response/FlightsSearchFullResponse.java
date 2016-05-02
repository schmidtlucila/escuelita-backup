package tp.services.flights.fsm.searchmethods.response;

import java.util.List;

public class FlightsSearchFullResponse {

    private ResponseStatus status;
    private List<ResultItem> items;
    private String currencyCode;

    public ResponseStatus getStatus() {
        return this.status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public List<ResultItem> getItems() {
        return this.items;
    }

    public void setItems(List<ResultItem> items) {
        this.items = items;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


}
