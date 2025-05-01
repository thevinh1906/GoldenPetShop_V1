package com.utc2.petShop.model.entities.Bill;

import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.entities.User.Employee;
import com.utc2.petShop.model.entities.User.User;
import com.utc2.petShop.model.implement.IBill;
import javafx.beans.property.*;

import java.time.LocalDate;

// Represents a bill for purchases
public class Bill implements IBill {
    private IntegerProperty id;
    private ObjectProperty<Customer> customer;
    private ObjectProperty<Employee> employee;
    private ObjectProperty<LocalDate> invoiceDate;
    private DoubleProperty totalAmount;
    private StringProperty paymentMethod;
    private StringProperty status;

    public Bill(int id, Customer customer, Employee employee, LocalDate invoiceDate, double totalAmount, String paymentMethod, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.customer = new SimpleObjectProperty<Customer>(customer);
        this.employee = new SimpleObjectProperty<Employee>(employee);
        this.invoiceDate = new SimpleObjectProperty<LocalDate>(invoiceDate);
        this.totalAmount = new SimpleDoubleProperty(totalAmount);
        this.paymentMethod = new SimpleStringProperty(paymentMethod);
        this.status = new SimpleStringProperty(status);
    }

    public Bill() {
        this.id = new SimpleIntegerProperty(0);
        this.customer = new SimpleObjectProperty<Customer>(new Customer());
        this.employee = new SimpleObjectProperty<Employee>(new Employee());
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

    public Customer getCustomer() {
        return customer.get();
    }

    public ObjectProperty<Customer> customerProperty() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer.set(customer);
    }

    public Employee getEmployee() {
        return employee.get();
    }

    public ObjectProperty<Employee> employeeProperty() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee.set(employee);
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
