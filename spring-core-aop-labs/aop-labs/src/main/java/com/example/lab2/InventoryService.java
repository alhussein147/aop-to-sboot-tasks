package com.example.lab2;

public interface InventoryService {
    int checkStock(String sku);

    void reserveStock(String sku, int qty);
}
