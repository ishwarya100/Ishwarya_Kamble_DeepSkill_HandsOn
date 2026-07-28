package com.cognizant.ormlearn.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;

@Service
public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepository stockRepository;

    @Transactional
    public List<Stock> getStockByCodeAndDateRange(String code, LocalDate startDate, LocalDate endDate) {
        LOGGER.info("Start");
        return stockRepository.findByCodeAndDateBetween(code, startDate, endDate);
    }

    @Transactional
    public List<Stock> getStockByCodeAboveClosePrice(String code, double close) {
        LOGGER.info("Start");
        return stockRepository.findByCodeAndCloseGreaterThan(code, close);
    }

    @Transactional
    public List<Stock> getTopThreeHighestVolume() {
        LOGGER.info("Start");
        return stockRepository.findTop3ByOrderByVolumeDesc();
    }

    @Transactional
    public List<Stock> getTopThreeLowestVolume(String code) {
        LOGGER.info("Start");
        return stockRepository.findTop3ByCodeOrderByVolumeAsc(code);
    }
}
