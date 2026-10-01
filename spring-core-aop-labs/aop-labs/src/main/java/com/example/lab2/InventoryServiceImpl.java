package com.example.lab2;

class InventoryServiceImpl implements InventoryService {
    public int checkStock(String sku) {
        System.out.println("[Impl] checkStock " + sku);
        return 42;
    }

    public void reserveStock(String sku, int qty) {
        if (qty > 100) throw new IllegalStateException("qty > 100");
        System.out.println("[Impl] reserved " + qty + " of " + sku);
    }
}