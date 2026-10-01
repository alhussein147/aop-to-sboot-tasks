package com.example.lab3;

public interface InventoryService {

    int checkStock(String sku);

    void reserveStock(String sku, int qty);
}
