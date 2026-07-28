package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryRepository.findAll();
        LOGGER.info("End");
        return countries;
    }

    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(countryCode);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found for code " + countryCode);
        }
        return result.get();
    }

    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    @Transactional
    public void updateCountry(String code, String name) throws CountryNotFoundException {
        Country country = findCountryByCode(code);
        country.setName(name);
        countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    // Search box scenario, matches partial country name
    @Transactional
    public List<Country> findCountriesByPartialName(String text) {
        return countryRepository.findByNameContainingOrderByNameAsc(text);
    }

    // Alphabet index scenario, matches countries starting with the given letter
    @Transactional
    public List<Country> findCountriesStartingWith(String letter) {
        return countryRepository.findByNameStartingWithOrderByNameAsc(letter);
    }
}
