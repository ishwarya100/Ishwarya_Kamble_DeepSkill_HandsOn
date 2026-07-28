package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Search box scenario: countries whose name contains the given text
    List<Country> findByNameContaining(String text);

    // Same search, enhanced to return the countries in ascending name order
    List<Country> findByNameContainingOrderByNameAsc(String text);

    // Alphabet index scenario: countries whose name starts with the given letter
    List<Country> findByNameStartingWithOrderByNameAsc(String letter);
}
