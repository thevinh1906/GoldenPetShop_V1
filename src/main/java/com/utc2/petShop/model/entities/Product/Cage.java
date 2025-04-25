package com.utc2.petShop.model.entities.Product;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cage extends Product {
    private StringProperty dimension;
    private StringProperty material;

    public Cage(int id, String name, double price, int quantity, String description, int supplierID, String manufacturer, String dimension, String material) {
        super(id, name, price, quantity, description, supplierID, manufacturer);
        this.dimension = new SimpleStringProperty(dimension);
        this.material = new SimpleStringProperty(material);
    }

    public Cage() {
        super();
        this.dimension = new SimpleStringProperty("");
        this.material = new SimpleStringProperty("");
    }

    public String getDimension() {
        return dimension.get();
    }

    public StringProperty dimensionProperty() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension.set(dimension);
    }

    public String getMaterial() {
        return material.get();
    }

    public StringProperty materialProperty() {
        return material;
    }

    public void setMaterial(String material) {
        this.material.set(material);
    }

    @Override
    public String toString() {
        return "Cage";
    }
}
