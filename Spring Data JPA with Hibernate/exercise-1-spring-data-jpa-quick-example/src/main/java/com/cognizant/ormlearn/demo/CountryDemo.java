package com.cognizant.ormlearn.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@Component
public class CountryDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryDemo.class);

    @Autowired
    private CountryService countryService;

    public void runDemo() throws CountryNotFoundException {
        testGetAllCountries();
        testFindCountryByCode();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
        testPartialNameSearch();
        testStartingWithLetter();
    }

    // list every country
    private void testGetAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("Country count={}", countries.size());
        LOGGER.info("End");
    }

    // find a country based on country code
    private void testFindCountryByCode() throws CountryNotFoundException {
        LOGGER.info("Start");
        Country country = countryService.findCountryByCode("IN");
        LOGGER.debug("Country:{}", country);
        LOGGER.info("End");
    }

    // add a new country
    private void testAddCountry() throws CountryNotFoundException {
        LOGGER.info("Start");
        countryService.addCountry(new Country("XX", "Testland"));
        Country country = countryService.findCountryByCode("XX");
        LOGGER.debug("Added country:{}", country);
        LOGGER.info("End");
    }

    // update a country based on code
    private void testUpdateCountry() throws CountryNotFoundException {
        LOGGER.info("Start");
        countryService.updateCountry("XX", "Testland Updated");
        Country country = countryService.findCountryByCode("XX");
        LOGGER.debug("Updated country:{}", country);
        LOGGER.info("End");
    }

    // delete a country based on code
    private void testDeleteCountry() {
        LOGGER.info("Start");
        countryService.deleteCountry("XX");
        LOGGER.info("End");
    }

    // search box scenario, ordered ascending by name
    private void testPartialNameSearch() {
        LOGGER.info("Start");
        List<Country> countries = countryService.findCountriesByPartialName("ou");
        LOGGER.debug("Countries containing 'ou':{}", countries);
        LOGGER.info("End");
    }

    // alphabet index scenario
    private void testStartingWithLetter() {
        LOGGER.info("Start");
        List<Country> countries = countryService.findCountriesStartingWith("Z");
        LOGGER.debug("Countries starting with 'Z':{}", countries);
        LOGGER.info("End");
    }
}
