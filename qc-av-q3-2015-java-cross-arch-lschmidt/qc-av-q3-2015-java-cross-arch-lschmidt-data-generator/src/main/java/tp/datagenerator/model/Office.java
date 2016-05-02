package tp.datagenerator.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Office {
    private Long oid;
    private String officeCode;
    private Long countryOid;
    private String countryCode;
    private String language;
    private String moneyPattern;
    private String currencySymbol;
    private String currencyCode;
    private Long currencyId;
    private String currencyDescription;
    private Locale locale;
    private NumberFormat numberFormat;
    private DecimalFormat decimalFormat;

    public Office() {
        super();
    }

    public Long getOid() {
        return this.oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getOfficeCode() {
        return this.officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public Long getCountryOid() {
        return this.countryOid;
    }

    public void setCountryOid(Long countryOid) {
        this.countryOid = countryOid;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMoneyPattern() {
        return this.moneyPattern;
    }

    public void setMoneyPattern(String moneyPattern) {
        this.moneyPattern = moneyPattern;
    }

    public String getCurrencySymbol() {
        return this.currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public NumberFormat getNumberFormat() {
        return this.numberFormat;
    }

    public void setNumberFormat(NumberFormat numberFormat) {
        this.numberFormat = numberFormat;
    }

    public DecimalFormat getDecimalFormat() {
        return this.decimalFormat;
    }

    public void setDecimalFormat(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    public Long getCurrencyId() {
        return this.currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyDescription() {
        return this.currencyDescription;
    }

    public void setCurrencyDescription(String currencyDescription) {
        this.currencyDescription = currencyDescription;
    }

    @Override
    public String toString() {
        return "Office [oid=" + this.oid + ", officeCode=" + this.officeCode + ", countryOid=" + this.countryOid
            + ", countryCode=" + this.countryCode + ", language=" + this.language + ", moneyPattern=" + this.moneyPattern
            + ", currencySymbol=" + this.currencySymbol + ", currencyCode=" + this.currencyCode + ", currencyId="
            + this.currencyId + ", currencyDescription=" + this.currencyDescription + ", locale=" + this.locale
            + ", numberFormat=" + this.numberFormat + ", decimalFormat=" + this.decimalFormat + "]";
    }
}
