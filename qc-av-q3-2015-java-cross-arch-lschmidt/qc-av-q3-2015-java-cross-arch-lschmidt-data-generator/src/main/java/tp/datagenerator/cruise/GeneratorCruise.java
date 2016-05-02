package tp.datagenerator.cruise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.despegar.automation.commons.config.model.ExecutionContextApp;
import com.despegar.automation.commons.config.model.OfficeData;

import tp.datagenerator.builders.OfficeBuilder;
import tp.datagenerator.model.Company;
import tp.datagenerator.model.CompanyDistributionData;
import tp.datagenerator.model.Currency;
import tp.datagenerator.model.Office;
import tp.datagenerator.model.OfficeCompanyData;
import tp.datagenerator.model.Region;
import dk.deck.testdatagenerator.DataGenerator;
import dk.deck.testdatagenerator.DataGeneratorFactory;

public class GeneratorCruise {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorCruise.class.getName());

    private GeneratorCruise() {
        super();
    }

    public static List<String> getOfficesCodes() {
        List<String> offices = new ArrayList<String>();
        offices.add("DESPEGAR_AR");
        offices.add("DESPEGAR_BR");
        offices.add("DESPEGAR_CL");
        offices.add("DESPEGAR_CO");
        offices.add("DESPEGAR_MX");
        return offices;
    }

    public static List<Office> getOffices(ExecutionContextApp executionContextApp) {
        Map<String, OfficeData> officesData = executionContextApp.getOfficesData();

        List<Office> offices = new ArrayList<Office>();

        Iterator<String> keySet = officesData.keySet().iterator();
        while (keySet.hasNext()) {
            String officeDataKey = keySet.next();
            Office office = OfficeBuilder.buildOfficeData(officesData.get(officeDataKey));
            offices.add(office);
        }
        return offices;
    }

    public static Map<String, Office> getOfficesKeyOfficeCodeAsMap(ExecutionContextApp executionContextApp) {
        List<Office> offices = GeneratorCruise.getOffices(executionContextApp);
        Map<String, Office> officesAsMap = new HashMap<String, Office>();
        for (Office office : offices) {
            officesAsMap.put(office.getOfficeCode(), office);
        }
        return officesAsMap;
    }

    public static List<Currency> getCurrencies(ExecutionContextApp executionContextApp) {

        List<Office> offices = GeneratorCruise.getOffices(executionContextApp);

        List<Currency> currencies = new ArrayList<Currency>();

        for (Office office : offices) {
            Currency currency = new Currency();
            currency.setId(office.getCurrencyId());
            currency.setCode(office.getCurrencyCode());
            currency.setDescription(office.getCurrencyDescription());
            currencies.add(currency);
        }

        currencies.add(new Currency(Long.parseLong("2"), "USD", "America (United States of America), Dollars"));
        currencies.add(new Currency(Long.parseLong("3"), "EUR", "Europe"));
        currencies.add(new Currency(Long.parseLong("6"), "CAD", "Canada, Dollars"));

        return currencies;
    }

    public static Map<String, Currency> getCurrenciesAsMapWithKeyCode(ExecutionContextApp executionContextApp) {
        List<Currency> currencies = getCurrencies(executionContextApp);
        Map<String, Currency> currenciesAsMap = new HashMap<String, Currency>();
        for (Currency currency : currencies) {
            currenciesAsMap.put(currency.getCode(), currency);
        }
        return currenciesAsMap;
    }

    public static Map<Long, Currency> getCurrenciesAsMapWithKeyId(ExecutionContextApp executionContextApp) {
        List<Currency> currencies = getCurrencies(executionContextApp);
        Map<Long, Currency> currenciesAsMap = new HashMap<Long, Currency>();
        for (Currency currency : currencies) {
            currenciesAsMap.put(currency.getId(), currency);
        }
        return currenciesAsMap;
    }

    public static List<String> getCompaniesCodes() {
        List<Company> companies = GeneratorCruise.getCompanies();
        List<String> companiesString = new ArrayList<String>();
        for (Company company : companies) {
            companiesString.add(company.getCode());
        }
        return companiesString;
    }

    @SuppressWarnings("unchecked")
    public static List<OfficeCompanyData> getOfficeCompanyData() {
        List<OfficeCompanyData> list = new ArrayList<OfficeCompanyData>();
        try {
            DataGenerator<OfficeCompanyData> generator = DataGeneratorFactory.getDataGenerator(OfficeCompanyData.class);
            Set<String> offices = new HashSet<String>(GeneratorCruise.getOfficesCodes());
            Set<String> companies = new HashSet<String>(GeneratorCruise.getCompaniesCodes());
            generator.setFieldValues("office", offices);
            generator.setFieldValues("company", companies);
            list = generator.generateData();
        } catch (Exception e) {
            LOGGER.error("Error while get office company data", e);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static List<CompanyDistributionData> getCompanyDistributionData() {
        List<CompanyDistributionData> list = new ArrayList<CompanyDistributionData>();
        try {
            DataGenerator<CompanyDistributionData> generator = DataGeneratorFactory
                .getDataGenerator(CompanyDistributionData.class);
            Set<Company> companies = new HashSet<Company>(GeneratorCruise.getCompanies());
            Set<String> distributions = new HashSet<String>(GeneratorCruise.getDistributionsSelected());
            generator.setFieldValues("company", companies);
            generator.setFieldValues("distribution", distributions);
            list = generator.generateData();
        } catch (Exception e) {
            LOGGER.error("Error while get company distribution data", e);
        }
        return list;
    }

    public static List<String> getDistributionsSelected() {
        List<String> distributions = new ArrayList<String>();
        distributions.add("1");
        distributions.add("2");
        distributions.add("3");
        distributions.add("4");
        distributions.add("3-0");
        distributions.add("2-0");
        distributions.add("2-1");
        distributions.add("1-1-10");
        distributions.add("2-0-15");
        return distributions;
    }

    public static List<Company> getCompanies() {
        List<Company> companies = new ArrayList<Company>();
        companies.add(new Company(Long.valueOf(1), "RCL", "Royal Caribbean", Long.valueOf("2")));
        companies.add(new Company(Long.valueOf(2), "CEL", "Celebrity Cruises", Long.valueOf("2")));
        companies.add(new Company(Long.valueOf(3), "AZA", "Azamara", Long.valueOf("2")));
        companies.add(new Company(Long.valueOf(4), "MSC", "MSC Cruceros", Long.valueOf("1")));
        companies.add(new Company(Long.valueOf(5), "CST", "Costa Cruceros", Long.valueOf("5")));
        companies.add(new Company(Long.valueOf(8), "IBE", "Ibero Cruceros", Long.valueOf("5")));
        companies.add(new Company(Long.valueOf(9), "CCL", "Carnival Cruise", Long.valueOf("3")));
        companies.add(new Company(Long.valueOf(10), "PUL", "Pullmantur", Long.valueOf("2")));
        companies.add(new Company(Long.valueOf(11), "NCL", "Norwegian Cruise Line", Long.valueOf("6")));
        companies.add(new Company(Long.valueOf(12), "97", "Cruzeiros 97 FM", Long.valueOf("1")));
        companies.add(new Company(Long.valueOf(13), "PRC", "Princess Cruises", Long.valueOf("4")));
        companies.add(new Company(Long.valueOf(14), "CND", "Cunard Cruise Line", Long.valueOf("4")));
        companies.add(new Company(Long.valueOf(15), "HLD", "Holland America Line", Long.valueOf("7")));
        companies.add(new Company(Long.valueOf(16), "DCL", "Disney Cruise Line", Long.valueOf("8")));
        return companies;
    }

    public static Map<Long, Company> getCompaniesAsMapWithKeyId() {
        List<Company> companies = getCompanies();
        Map<Long, Company> companiesAsMap = new HashMap<Long, Company>();
        for (Company company : companies) {
            companiesAsMap.put(company.getId(), company);
        }
        return companiesAsMap;
    }

    public static Map<String, Company> getCompaniesAsMapWithKeyCode() {
        List<Company> companies = getCompanies();
        Map<String, Company> companiesAsMap = new HashMap<String, Company>();
        for (Company company : companies) {
            companiesAsMap.put(company.getCode(), company);
        }
        return companiesAsMap;
    }

    public static List<Region> getRegions() {
        List<Region> regions = new ArrayList<Region>();
        regions.add(new Region("AFR", "África"));
        regions.add(new Region("AK", "Alaska"));
        regions.add(new Region("NOA", "América del Norte"));
        regions.add(new Region("SOA", "América del Sur"));
        regions.add(new Region("ANT", "Antártica"));
        regions.add(new Region("ARG", "Argentina"));
        regions.add(new Region("ARC", "Ártico"));
        regions.add(new Region("ASA", "Asia"));
        regions.add(new Region("BAH", "Bahamas"));
        regions.add(new Region("BRA", "Brasil"));
        regions.add(new Region("CAR", "Caribe"));
        regions.add(new Region("COA", "Centroamérica"));
        regions.add(new Region("CHI", "Chile"));
        regions.add(new Region("EMA", "Emiratos Árabes"));
        regions.add(new Region("EUR", "Europa"));
        regions.add(new Region("MED", "Mediterráneo"));
        regions.add(new Region("MEX", "México"));
        regions.add(new Region("MIA", "Miami"));
        regions.add(new Region("OCE", "Oceanía"));
        return regions;
    }

}
