package com.example.inventoryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StockLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Integer trackedStock;

    public StockLevel() {
    }

    public StockLevel(Long productId, Integer trackedStock) {
        this.productId = productId;
        this.trackedStock = trackedStock;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getTrackedStock() {
        return trackedStock;
    }

    public void setTrackedStock(Integer trackedStock) {
        this.trackedStock = trackedStock;
    }
}
