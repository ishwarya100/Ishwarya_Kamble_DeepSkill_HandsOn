package com.cognizant.ormlearn.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;

@Component
public class CountryDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryDemo.class);

    @Autowired
    private CountryService countryService;

    public void runDemo() {
        testPartialNameSearch();
        testStartingWithLetter();
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
