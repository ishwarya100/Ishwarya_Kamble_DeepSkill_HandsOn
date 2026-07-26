package com.example.inventoryservice.repository;

import com.example.inventoryservice.entity.StockLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockLevelRepository extends JpaRepository<StockLevel, Long> {
}
