package com.utc2.petShop.model.entities.Bill;

import com.utc2.petShop.model.implement.IBill;
import javafx.beans.property.*;

import java.time.LocalDate;

// Represents a bill for purchases
public class Bill implements IBill {
    private IntegerProperty id;
    private IntegerProperty customerId;
    private IntegerProperty userId;
    private ObjectProperty<LocalDate> invoiceDate;
    private DoubleProperty totalAmount;
    private StringProperty paymentMethod;
    private StringProperty status;

    public Bill(int id, int customerId, int userId, LocalDate invoiceDate, double totalAmount, String paymentMethod, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.userId = new SimpleIntegerProperty(userId);
        this.invoiceDate = new SimpleObjectProperty<LocalDate>(invoiceDate);
        this.totalAmount = new SimpleDoubleProperty(totalAmount);
        this.paymentMethod = new SimpleStringProperty(paymentMethod);
        this.status = new SimpleStringProperty(status);
    }

    public Bill() {
        this.id = new SimpleIntegerProperty(0);
        this.customerId = new SimpleIntegerProperty(0);
        this.userId = new SimpleIntegerProperty(0);
        this.invoiceDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
        this.totalAmount = new SimpleDoubleProperty(0);
        this.paymentMethod = new SimpleStringProperty("");
        this.status = new SimpleStringProperty("");
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

    public int getCustomerId() {
        return customerId.get();
    }

    public IntegerProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public int getUserId() {
        return userId.get();
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate.get();
    }

    public ObjectProperty<LocalDate> invoiceDateProperty() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate.set(invoiceDate);
    }

    public double getTotalAmount() {
        return totalAmount.get();
    }

    public DoubleProperty totalAmountProperty() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount.set(totalAmount);
    }

    public String getPaymentMethod() {
        return paymentMethod.get();
    }

    public StringProperty paymentMethodProperty() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod.set(paymentMethod);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
// phương thức cần thiết
    @Override
    public void createBill() {

    }

    @Override
    public void getBillDetails() {

    }
}
