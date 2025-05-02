package com.utc2.petShop.model.entities.Promotion;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Promotion {
    private IntegerProperty id;
    private StringProperty name;
    private DoubleProperty discountPercent;
    private ObjectProperty<LocalDate> startDate;
    private ObjectProperty<LocalDate> endDate;
    private BooleanProperty isActive;

    public Promotion(int id, String name, double discountPercent, LocalDate startDate, LocalDate endDate, boolean isActive) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.discountPercent = new SimpleDoubleProperty(discountPercent);
        this.startDate = new SimpleObjectProperty<LocalDate>(startDate);
        this.endDate = new SimpleObjectProperty<LocalDate>(endDate);
        this.isActive = new SimpleBooleanProperty(isActive);
    }

    public Promotion() {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.discountPercent = new SimpleDoubleProperty(0);
        this.startDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
        this.endDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
        this.isActive = new SimpleBooleanProperty(false);
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

    public double getDiscountPercent() {
        return discountPercent.get();
    }

    public DoubleProperty discountPercentProperty() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent.set(discountPercent);
    }

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

    public LocalDate getEndDate() {
        return endDate.get();
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate.set(endDate);
    }

    public boolean isIsActive() {
        return isActive.get();
    }

    public BooleanProperty isActiveProperty() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive.set(isActive);
    }
}
