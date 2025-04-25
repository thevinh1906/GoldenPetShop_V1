package com.utc2.petShop.model.entities.Product;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Toy extends Product{
    private StringProperty material;
    private StringProperty size;

    public Toy(int id, String name, double price, int quantity, String description, int supplierID, String manufacturer, String material, String size) {
        super(id, name, price, quantity, description, supplierID, manufacturer);
        this.material = new SimpleStringProperty(material);
        this.size = new SimpleStringProperty(size);
    }

    public Toy() {
        super();
        this.material = new SimpleStringProperty("");
        this.size = new SimpleStringProperty("");
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

    public String getSize() {
        return size.get();
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    @Override
    public String toString() {
        return "Toy";
    }
}
