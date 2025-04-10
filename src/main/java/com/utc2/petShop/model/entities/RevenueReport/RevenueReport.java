package com.utc2.petShop.model.entities.RevenueReport;

import javafx.beans.property.*;

public class RevenueReport {
    private IntegerProperty id;
    private IntegerProperty month;
    private IntegerProperty year;
    private DoubleProperty totalRevenue;
    private IntegerProperty totalBill;

    public RevenueReport(int id, int month, int year, double totalRevenue, int totalBill) {
        this.id = new SimpleIntegerProperty(id);
        this.month = new SimpleIntegerProperty(month);
        this.year = new SimpleIntegerProperty(year);
        this.totalRevenue = new SimpleDoubleProperty(totalRevenue);
        this.totalBill = new SimpleIntegerProperty(totalBill);
    }

    public RevenueReport() {
        this.id = new SimpleIntegerProperty(0);
        this.month = new SimpleIntegerProperty(0);
        this.year = new SimpleIntegerProperty(0);
        this.totalRevenue = new SimpleDoubleProperty(0);
        this.totalBill = new SimpleIntegerProperty(0);
    }

    public int getTotalBill() {
        return totalBill.get();
    }

    public IntegerProperty totalBillProperty() {
        return totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill.set(totalBill);
    }

    public double getTotalRevenue() {
        return totalRevenue.get();
    }

    public DoubleProperty totalRevenueProperty() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue.set(totalRevenue);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public int getMonth() {
        return month.get();
    }

    public IntegerProperty monthProperty() {
        return month;
    }

    public void setMonth(int month) {
        this.month.set(month);
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
}
