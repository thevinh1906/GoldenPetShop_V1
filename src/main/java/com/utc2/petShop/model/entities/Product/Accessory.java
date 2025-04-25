package com.utc2.petShop.model.entities.Product;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Accessory extends Product {
    private StringProperty type;
    private StringProperty brand;

    public Accessory(int id, String name, double price, int quantity, String description, int supplierID, String manufacturer, String type, String brand) {
        super(id, name, price, quantity, description, supplierID, manufacturer);
        this.type = new SimpleStringProperty(type);
        this.brand = new SimpleStringProperty(brand);
    }

    public Accessory() {
        super();
        this.type = new SimpleStringProperty("");
        this.brand = new SimpleStringProperty("");
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getBrand() {
        return brand.get();
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    @Override
    public String toString() {
        return "Accessory";
    }
}



