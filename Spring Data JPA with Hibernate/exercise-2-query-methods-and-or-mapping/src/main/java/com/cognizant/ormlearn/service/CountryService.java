package com.cognizant.ormlearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // Search box scenario, matches partial country name, ascending order
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
