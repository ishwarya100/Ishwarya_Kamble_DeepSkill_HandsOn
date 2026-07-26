package com.example.inventoryservice.controller;

import com.example.inventoryservice.client.ProductClient;
import com.example.inventoryservice.dto.ProductDto;
import com.example.inventoryservice.entity.StockLevel;
import com.example.inventoryservice.repository.StockLevelRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final StockLevelRepository stockLevelRepository;
    private final ProductClient productClient;

    public InventoryController(StockLevelRepository stockLevelRepository, ProductClient productClient) {
        this.stockLevelRepository = stockLevelRepository;
        this.productClient = productClient;
    }

    @GetMapping
    public List<StockLevel> getAllStockLevels() {
        return stockLevelRepository.findAll();
    }

    // fetches live product info via Eureka + Feign, then records tracked stock
    @PostMapping("/track/{productId}")
    public StockLevel trackProduct(@PathVariable Long productId) {
        ProductDto product = productClient.getProductById(productId);
        StockLevel stockLevel = new StockLevel(product.getId(), product.getStock());
        return stockLevelRepository.save(stockLevel);
    }
}
