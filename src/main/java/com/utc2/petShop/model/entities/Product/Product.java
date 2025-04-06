package com.utc2.petShop.model.entities.Product;

import javafx.beans.property.*;

// Represents products sold in the pet store
public class Product {
    private IntegerProperty id;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty quantity;
    private StringProperty description;
    private IntegerProperty SupplierID;

    public Product(int id, String name, double price, int quantity, String description, int supplierID) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.description = new SimpleStringProperty(description);
        SupplierID = new SimpleIntegerProperty(supplierID);
    }

    public Product() {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.price = new SimpleDoubleProperty(0);
        this.quantity = new SimpleIntegerProperty(0);
        this.description = new SimpleStringProperty("");
        SupplierID = new SimpleIntegerProperty(0);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getSupplierID() {
        return SupplierID.get();
    }

    public IntegerProperty supplierIDProperty() {
        return SupplierID;
    }

    public void setSupplierID(int supplierID) {
        this.SupplierID.set(supplierID);
    }
}

