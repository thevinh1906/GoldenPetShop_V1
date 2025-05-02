package com.utc2.petShop.model.entities.User;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Employee extends User {
    private ObjectProperty<EEmployeePosition> position;
    private DoubleProperty salary;
    private StringProperty workingHours;

    public Employee(int id, String username, String password, String name, boolean gender, String email, String phoneNumber, String address, LocalDate birthDay, LocalDate creationDate, EEmployeePosition position, double salary, String workingHours) {
        super(id, username, password, name, gender, email, phoneNumber, address, birthDay, creationDate);
        this.position = new SimpleObjectProperty<EEmployeePosition>(position);
        this.salary = new SimpleDoubleProperty(salary);
        this.workingHours = new SimpleStringProperty(workingHours);
    }

    public Employee() {
        super();
        this.position = new SimpleObjectProperty<EEmployeePosition>();
        this.salary = new SimpleDoubleProperty(0);
        this.workingHours = new SimpleStringProperty("");
    }

    public EEmployeePosition getPosition() {
        return position.get();
    }

    public ObjectProperty<EEmployeePosition> positionProperty() {
        return position;
    }

    public void setPosition(EEmployeePosition position) {
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

    @Override
    public String toString() {
        return getName();
    }
}
