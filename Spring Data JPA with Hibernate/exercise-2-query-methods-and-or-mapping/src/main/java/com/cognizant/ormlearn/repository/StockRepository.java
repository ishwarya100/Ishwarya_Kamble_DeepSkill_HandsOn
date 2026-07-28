package com.cognizant.ormlearn.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    // All stock rows for a given code between two dates, e.g. FB in September 2019
    List<Stock> findByCodeAndDateBetween(String code, LocalDate startDate, LocalDate endDate);

    // All stock rows for a given code where the closing price is above a threshold
    List<Stock> findByCodeAndCloseGreaterThan(String code, double close);

    // Top 3 dates with the highest volume of transactions across all stocks
    List<Stock> findTop3ByOrderByVolumeDesc();

    // Top 3 dates with the lowest volume of transactions for a given stock code
    List<Stock> findTop3ByCodeOrderByVolumeAsc(String code);
}
