package com.utc2.petShop.model.entities.Product;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Food extends Product{
    private ObjectProperty<LocalDate> expirationDate;
    private StringProperty flavor;

    public Food(int id, String name, double price, int quantity, String description, int supplierID, LocalDate expirationDate, String flavor) {
        super(id, name, price, quantity, description, supplierID);
        this.expirationDate = new SimpleObjectProperty<>(expirationDate);
        this.flavor = new SimpleStringProperty(flavor);
    }

    public Food() {
        super();
        this.expirationDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
        this.flavor = new SimpleStringProperty("");
    }

    public LocalDate getExpirationDate() {
        return expirationDate.get();
    }

    public ObjectProperty<LocalDate> expirationDateProperty() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate.set(expirationDate);
    }

    public String getFlavor() {
        return flavor.get();
    }

    public StringProperty flavorProperty() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor.set(flavor);
    }
}
