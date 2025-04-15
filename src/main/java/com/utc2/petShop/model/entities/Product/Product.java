package com.utc2.petShop.model.entities.Product;

import com.utc2.petShop.model.implement.IProduct;
import javafx.beans.property.*;

// Represents products sold in the pet store
public class Product implements IProduct {
    private IntegerProperty id;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty quantity;
    private StringProperty description;
    private IntegerProperty SupplierID;
    private StringProperty manufacturer;

    public Product(int id, String name, double price, int quantity, String description, int supplierID, String manufacturer) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.description = new SimpleStringProperty(description);
        SupplierID = new SimpleIntegerProperty(supplierID);
        this.manufacturer = new SimpleStringProperty(manufacturer);
    }

    public Product() {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.price = new SimpleDoubleProperty(0);
        this.quantity = new SimpleIntegerProperty(0);
        this.description = new SimpleStringProperty("");
        SupplierID = new SimpleIntegerProperty(0);
        this.manufacturer = new SimpleStringProperty("");
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

    public String getManufacturer() {
        return manufacturer.get();
    }

    public StringProperty manufacturerProperty() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer.set(manufacturer);
    }
// phương thức cần thiết
    @Override
    public void updateStock(int quantity) {

    }

    @Override
    public String getProductInfo() {
        return "";
    }
}

