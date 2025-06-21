package com.utc2.petShop.model.entities.Product;

import com.utc2.petShop.model.entities.Image.ImageByte;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.List;

public class Food extends Product {
    private ObjectProperty<LocalDate> expirationDate;
    private StringProperty flavor;

    public Food(int id, String name, double price, int quantity, String description, Supplier supplier, String manufacturer, LocalDate expirationDate, String flavor, List<ImageByte> images) {
        super(id, name, price, quantity, description, supplier, manufacturer, images);
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

    @Override
    public String toString() {
        return "Food";
    }
}
