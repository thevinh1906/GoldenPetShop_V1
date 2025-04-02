package com.utc2.petShop.model.entities;

import java.util.Date;

// Represents a bill for purchases
public class Bill {
    private int billId;
    private int quantity;
    private Date date;
    private double totalAmount;
    private String paymentMethod;

    // Creates a bill
    public void createBill() {
        // Business logic for bill creation
    }

    // Retrieves bill details
    public void getBillDetails() {
        // Return details as needed
    }
}
