package tp.services.flights.fsm.searchmethods.response;

public class TotalDetail {

    private int baseFare;
    private int totalFare;
    private int taxes;
    private int charges;
    private int fees;

    public int getFees() {
        return this.fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public int getCharges() {
        return this.charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }

    public int getTaxes() {
        return this.taxes;
    }

    public void setTaxes(int taxes) {
        this.taxes = taxes;
    }

    public int getTotalFare() {
        return this.totalFare;
    }

    public void setTotalFare(int totalFare) {
        this.totalFare = totalFare;
    }

    public int getBaseFare() {
        return this.baseFare;
    }

    public void setBaseFare(int baseFare) {
        this.baseFare = baseFare;
    }

}
