package com.utc2.petShop.model.entities.Product;

import com.utc2.petShop.model.entities.Image.ImageByte;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.util.List;

public class Toy extends Product{
    private StringProperty material;
    private StringProperty size;

    public Toy(int id, String name, double price, int quantity, String description, Supplier supplier, String manufacturer, String material, String size, List<ImageByte> images) {
        super(id, name, price, quantity, description, supplier, manufacturer, images);
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
