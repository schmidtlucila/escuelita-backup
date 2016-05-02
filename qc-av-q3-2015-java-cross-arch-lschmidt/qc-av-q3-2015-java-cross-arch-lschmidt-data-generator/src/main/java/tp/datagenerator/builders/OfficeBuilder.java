package tp.datagenerator.builders;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;

import com.despegar.automation.commons.config.model.Configuration;
import com.despegar.automation.commons.config.model.OfficeData;

import tp.datagenerator.model.Office;

public class OfficeBuilder {

    private OfficeBuilder() {
        super();
    }

    public static Office buildOfficeData(OfficeData officeData) {
        Map<String, String> keys = officeData.getKeys();

        Locale locale = new Locale(keys.get("language"), keys.get("country"));
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.applyPattern(keys.get("moneyPattern"));

        Office office = new Office();
        office.setOid(Long.parseLong(keys.get("id")));
        office.setOfficeCode(keys.get("officeCode"));
        office.setCountryCode(keys.get("country"));
        office.setCountryOid(Long.parseLong(keys.get("countryId")));
        office.setLanguage(keys.get("language"));
        office.setCurrencyId(Long.parseLong(keys.get("currencyId")));
        office.setCurrencyCode(Currency.getInstance(locale).getCurrencyCode());
        office.setCurrencyDescription(keys.get("currencyDescription"));
        office.setMoneyPattern(keys.get("moneyPattern"));
        office.setLocale(locale);
        office.setNumberFormat(numberFormat);
        office.setDecimalFormat(decimalFormat);
        return office;
    }

    public static Office buildOfficeData(Configuration configuration) {
        return OfficeBuilder.buildOfficeData(configuration.getOfficeData());
    }

}
