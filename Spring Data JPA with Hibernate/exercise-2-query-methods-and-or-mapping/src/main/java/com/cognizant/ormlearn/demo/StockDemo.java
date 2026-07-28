package com.cognizant.ormlearn.demo;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.service.StockService;

@Component
public class StockDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockDemo.class);

    @Autowired
    private StockService stockService;

    public void runDemo() {
        testFacebookSeptember2019();
        testGoogleAboveClosePrice();
        testTopThreeHighestVolume();
        testNetflixLowestVolume();
    }

    // Facebook stock details for the month of September 2019
    private void testFacebookSeptember2019() {
        LOGGER.info("Start");
        List<Stock> stocks = stockService.getStockByCodeAndDateRange(
                "FB", LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 30));
        LOGGER.debug("FB September 2019:{}", stocks);
        LOGGER.info("End");
    }

    // Google stock details where the closing price was greater than 1250
    private void testGoogleAboveClosePrice() {
        LOGGER.info("Start");
        List<Stock> stocks = stockService.getStockByCodeAboveClosePrice("GOOGL", 1250);
        LOGGER.debug("GOOGL close > 1250:{}", stocks);
        LOGGER.info("End");
    }

    // Top 3 dates with the highest volume of transactions
    private void testTopThreeHighestVolume() {
        LOGGER.info("Start");
        List<Stock> stocks = stockService.getTopThreeHighestVolume();
        LOGGER.debug("Top 3 highest volume:{}", stocks);
        LOGGER.info("End");
    }

    // Three dates when Netflix stocks had the lowest volume
    private void testNetflixLowestVolume() {
        LOGGER.info("Start");
        List<Stock> stocks = stockService.getTopThreeLowestVolume("NFLX");
        LOGGER.debug("NFLX lowest volume:{}", stocks);
        LOGGER.info("End");
    }
}
