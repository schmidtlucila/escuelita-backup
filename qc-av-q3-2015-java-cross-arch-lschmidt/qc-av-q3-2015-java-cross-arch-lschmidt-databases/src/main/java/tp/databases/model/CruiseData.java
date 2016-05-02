package tp.databases.model;

import java.util.Date;

public class CruiseData {

    private Date departureDate;
    private Long departureId;
    private Long cabinCategoryId;
    private Long deckId;
    private Long blockedCategoryId;
    private Long companyContractId;
    private Integer duration;
    private Long itineraryId;
    private Long shipId;
    private String cabinCode;
    private String companyCode;

    public CruiseData() {
        super();
    }

    public Date getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Long getDepartureId() {
        return this.departureId;
    }

    public void setDepartureId(Long departureId) {
        this.departureId = departureId;
    }

    public Long getCabinCategoryId() {
        return this.cabinCategoryId;
    }

    public void setCabinCategoryId(Long cabinCategoryId) {
        this.cabinCategoryId = cabinCategoryId;
    }

    public Long getDeckId() {
        return this.deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }

    public Long getBlockedCategoryId() {
        return this.blockedCategoryId;
    }

    public void setBlockedCategoryId(Long blockedCategoryId) {
        this.blockedCategoryId = blockedCategoryId;
    }

    public Long getCompanyContractId() {
        return this.companyContractId;
    }

    public void setCompanyContractId(Long companyContractId) {
        this.companyContractId = companyContractId;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Long getItineraryId() {
        return this.itineraryId;
    }

    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
    }

    public Long getShipId() {
        return this.shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public String getCabinCode() {
        return this.cabinCode;
    }

    public void setCabinCode(String cabinCode) {
        this.cabinCode = cabinCode;
    }

    public String getCompanyCode() {
        return this.companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }


    @Override
    public String toString() {
        return "CruiseData [departureDate=" + this.departureDate + ", departureId=" + this.departureId
            + ", cabinCategoryId=" + this.cabinCategoryId + ", deckId=" + this.deckId + ", blockedCategoryId="
            + this.blockedCategoryId + ", companyContractId=" + this.companyContractId + ", duration=" + this.duration
            + ", itineraryId=" + this.itineraryId + ", shipId=" + this.shipId + ", cabinCode=" + this.cabinCode
            + ", companyCode=" + this.companyCode + "]";
    }


}
