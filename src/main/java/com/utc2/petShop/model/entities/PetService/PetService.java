package com.utc2.petShop.model.entities.PetService;

import javafx.beans.property.*;

public class PetService {
    private IntegerProperty id;
    private StringProperty namePet;
    private StringProperty gender;
    private IntegerProperty age;
    private StringProperty customer;
    private StringProperty service;
    private StringProperty vaccines;
    private StringProperty healthStatus;
    private DoubleProperty weight;
    private StringProperty breed;
    private StringProperty animal;
    private StringProperty dateOfVisit;
    private StringProperty status;
    private DoubleProperty serviceCost;
    private StringProperty note;

    public PetService(int id, String namePet, String gender, int age, String customer,
                    String service, String vaccines, String healthStatus, double weight,
                    String breed, String animal, String dateOfVisit, String status,
                    double serviceCost, String note) {
        this.id = new SimpleIntegerProperty(id);
        this.namePet = new SimpleStringProperty(namePet);
        this.gender = new SimpleStringProperty(gender);
        this.age = new SimpleIntegerProperty(age);
        this.customer = new SimpleStringProperty(customer);
        this.service = new SimpleStringProperty(service);
        this.vaccines = new SimpleStringProperty(vaccines);
        this.healthStatus = new SimpleStringProperty(healthStatus);
        this.weight = new SimpleDoubleProperty(weight);
        this.breed = new SimpleStringProperty(breed);
        this.animal = new SimpleStringProperty(animal);
        this.dateOfVisit = new SimpleStringProperty(dateOfVisit);
        this.status = new SimpleStringProperty(status);
        this.serviceCost = new SimpleDoubleProperty(serviceCost);
        this.note = new SimpleStringProperty(note);
    }

    public PetService() {
        this(0, "", "", 0, "", "", "", "", 0.0, "", "", "", "", 0.0, "");
    }
}
