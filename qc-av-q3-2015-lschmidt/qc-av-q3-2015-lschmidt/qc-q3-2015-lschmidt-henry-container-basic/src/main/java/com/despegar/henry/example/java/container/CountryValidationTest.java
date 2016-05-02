package com.despegar.henry.example.java.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.despegar.henry.container.entities.ResultCode;
import com.despegar.henry.container.entities.ResultInfo;
import com.despegar.henry.container.tasks.TestTask;

public class CountryValidationTest
    extends TestTask {

    Map<String, String> countriesCapitals;

    @Override
    protected void doExecute(Map<String, Object> parameters) {
        try {
            // Get params
            String country = this.getName();
            String capital = parameters.get("capital").toString();
            String continent = parameters.get("continent").toString();
            Integer population = Integer.parseInt(parameters.get("population").toString());

            // Log params
            System.out.println("country: " + this.getName());
            Iterator<Entry<String, Object>> iterator = parameters.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> item = iterator.next();
                System.out.println(item.getKey() + ": " + item.getValue());
            }

            // Set returnedData
            Map<String, Object> returnedData = new HashMap<String, Object>();
            returnedData.put("country", country);
            this.setData(returnedData);

            // Verifications
            this.loadCountriesData();
            String expectedCapital = this.countriesCapitals.get(country);
            this.assertTrue("Verifying input capital is valid for that country", expectedCapital.equals(capital));
            this.assertTrue("Verifying population is greater than 1 million", population > 1);
            this.assertTrue("Verifying that selected country belongs to South America", continent.equals("South America"));

        } catch (Exception e) {
            this.registerActivity(e.getMessage(), ResultCode.ERROR);
            e.printStackTrace();
        }
    }

    private void loadCountriesData() {
        this.countriesCapitals = new HashMap<String, String>();
        this.countriesCapitals.put("Argentina", "Buenos Aires");
        this.countriesCapitals.put("Brazil", "Brasilia");
        this.countriesCapitals.put("United_States", "Washington");
        this.countriesCapitals.put("Bolivia", "La Paz");
        this.countriesCapitals.put("Perú", "Lima");
        this.countriesCapitals.put("China", "Hong Kong");
    }

    public void registerActivity(String message, ResultCode code) {
        ResultInfo ri = new ResultInfo();
        ri.setMessage(message);
        ri.setCode(code);
        this.registerResult(ri);
        this.setCode(ri.getCode());
    }

}
