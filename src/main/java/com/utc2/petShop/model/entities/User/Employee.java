package com.utc2.petShop.model.entities.User;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Employee extends User {
    private StringProperty position;
    private DoubleProperty salary;
    private StringProperty workingHours;

    public Employee(int id, String username, String password, String name, boolean gender, String email, String phoneNumber, String address, LocalDate birthDay, LocalDate creationDate, String position, double salary, String workingHours) {
        super(id, username, password, name, gender, email, phoneNumber, address, birthDay, creationDate);
        this.position = new SimpleStringProperty(position);
        this.salary = new SimpleDoubleProperty(salary);
        this.workingHours = new SimpleStringProperty(workingHours);
    }

    public Employee() {
        super();
        this.position = new SimpleStringProperty("");
        this.salary = new SimpleDoubleProperty(0);
        this.workingHours = new SimpleStringProperty("");
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public double getSalary() {
        return salary.get();
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }

    public String getWorkingHours() {
        return workingHours.get();
    }

    public StringProperty workingHoursProperty() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours.set(workingHours);
    }
}
