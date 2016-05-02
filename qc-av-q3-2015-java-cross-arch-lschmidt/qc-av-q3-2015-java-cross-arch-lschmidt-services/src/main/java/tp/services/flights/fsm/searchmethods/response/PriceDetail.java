package tp.services.flights.fsm.searchmethods.response;

public class PriceDetail {

    private String currencyCode;
    private TotalDetail total;
    private PassengerTypeDetail adult;
    private PassengerTypeDetail child;
    private PassengerTypeDetail infant;

    public PassengerTypeDetail getAdult() {
        return this.adult;
    }

    public void setAdult(PassengerTypeDetail adult) {
        this.adult = adult;
    }

    public TotalDetail getTotal() {
        return this.total;
    }

    public void setTotal(TotalDetail total) {
        this.total = total;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public PassengerTypeDetail getInfant() {
        return infant;
    }

    public void setInfant(PassengerTypeDetail infant) {
        this.infant = infant;
    }

    public PassengerTypeDetail getChild() {
        return child;
    }

    public void setChild(PassengerTypeDetail child) {
        this.child = child;
    }

}
