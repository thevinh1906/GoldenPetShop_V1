package com.utc2.petShop.model.entities.Service;


import javafx.beans.property.*;

public class Service {
    private IntegerProperty serviceId;
    private StringProperty serviceName;
    private StringProperty description;
    private DoubleProperty price;
    private StringProperty applicableSpecies;

    public Service() {
        this.serviceId = new SimpleIntegerProperty();
        this.serviceName = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.applicableSpecies = new SimpleStringProperty();
    }

    public Service(int serviceId, String serviceName, String description, double price, int durationMinutes, String applicableSpecies) {
        this.serviceId = new SimpleIntegerProperty(serviceId);
        this.serviceName = new SimpleStringProperty(serviceName);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price);
        this.applicableSpecies = new SimpleStringProperty(applicableSpecies);
    }

    public int getServiceId() {
        return serviceId.get();
    }

    public IntegerProperty serviceIdProperty() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId.set(serviceId);
    }

    public String getServiceName() {
        return serviceName.get();
    }

    public StringProperty serviceNameProperty() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName.set(serviceName);
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

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getApplicableSpecies() {
        return applicableSpecies.get();
    }

    public StringProperty applicableSpeciesProperty() {
        return applicableSpecies;
    }

    public void setApplicableSpecies(String applicableSpecies) {
        this.applicableSpecies.set(applicableSpecies);
    }
}
