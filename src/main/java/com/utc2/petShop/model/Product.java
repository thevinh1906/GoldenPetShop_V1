package com.utc2.petShop.model;

// Represents products sold in the pet store
public class Product {
    private int productId;
    private int supplierId;
    private String name;
    private String category;
    private double price;
    private int stockQuantity;

    // Updates stock after a sale
    public void updateStock(int quantity) {
        this.stockQuantity -= quantity;
    }

    // Retrieves product information
    public String getProductInfo() {
        return name + " - " + category + " ($" + price + ")";
    }
}
