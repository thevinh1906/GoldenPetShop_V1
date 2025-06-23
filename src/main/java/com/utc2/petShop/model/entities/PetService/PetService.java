package com.utc2.petShop.model.entities.PetService;

import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.entities.Service.Service;
import com.utc2.petShop.model.entities.vaccine.Vaccine;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class PetService {
    private IntegerProperty id;
    private StringProperty namePet;
    private StringProperty gender;
    private IntegerProperty age;
    private ObjectProperty<Customer> customer;
    private ObjectProperty<Vaccine> vaccines;
    private StringProperty healthStatus;
    private DoubleProperty weight;
    private StringProperty breed;
    private StringProperty animal;
    private ObjectProperty<LocalDate> dateOfVisit;
    private StringProperty status;
    private DoubleProperty serviceCost;
    private StringProperty note;
    private ListProperty<Service> services;

    public PetService(int id, String namePet, String gender, int age, Customer customer,
                      String service, Vaccine vaccines, String healthStatus, double weight,
                      String breed, String animal, LocalDate dateOfVisit, String status,
                      double serviceCost, String note, List<Service> services) {
        this.id = new SimpleIntegerProperty(id);
        this.namePet = new SimpleStringProperty(namePet);
        this.gender = new SimpleStringProperty(gender);
        this.age = new SimpleIntegerProperty(age);
        this.customer = new SimpleObjectProperty<>(customer);
        this.vaccines = new SimpleObjectProperty<>(vaccines);
        this.healthStatus = new SimpleStringProperty(healthStatus);
        this.weight = new SimpleDoubleProperty(weight);
        this.breed = new SimpleStringProperty(breed);
        this.animal = new SimpleStringProperty(animal);
        this.dateOfVisit = new SimpleObjectProperty<>(dateOfVisit);
        this.status = new SimpleStringProperty(status);
        this.serviceCost = new SimpleDoubleProperty(serviceCost);
        this.note = new SimpleStringProperty(note);
        this.services = new SimpleListProperty<>(FXCollections.observableArrayList(services));
    }

    public PetService() {
        this.id = new SimpleIntegerProperty(0);
        this.namePet = new SimpleStringProperty("");
        this.gender = new SimpleStringProperty("");
        this.age = new SimpleIntegerProperty(0);
        this.customer = new SimpleObjectProperty<>(new Customer());
        this.vaccines = new SimpleObjectProperty<>(new Vaccine());
        this.healthStatus = new SimpleStringProperty("");
        this.weight = new SimpleDoubleProperty(0.0);
        this.breed = new SimpleStringProperty("");
        this.animal = new SimpleStringProperty("");
        this.dateOfVisit = new SimpleObjectProperty<>(LocalDate.now());
        this.status = new SimpleStringProperty("");
        this.serviceCost = new SimpleDoubleProperty(0.0);
        this.note = new SimpleStringProperty("");
        this.services = new SimpleListProperty<>(FXCollections.observableArrayList());
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

    public String getNamePet() {
        return namePet.get();
    }

    public StringProperty namePetProperty() {
        return namePet;
    }

    public void setNamePet(String namePet) {
        this.namePet.set(namePet);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
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

    public Vaccine getVaccines() {
        return vaccines.get();
    }

    public ObjectProperty<Vaccine> vaccinesProperty() {
        return vaccines;
    }

    public void setVaccines(Vaccine vaccines) {
        this.vaccines.set(vaccines);
    }

    public String getHealthStatus() {
        return healthStatus.get();
    }

    public StringProperty healthStatusProperty() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus.set(healthStatus);
    }

    public double getWeight() {
        return weight.get();
    }

    public DoubleProperty weightProperty() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }

    public String getBreed() {
        return breed.get();
    }

    public StringProperty breedProperty() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed.set(breed);
    }

    public String getAnimal() {
        return animal.get();
    }

    public StringProperty animalProperty() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal.set(animal);
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit.get();
    }

    public ObjectProperty<LocalDate> dateOfVisitProperty() {
        return dateOfVisit;
    }

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit.set(dateOfVisit);
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

    public double getServiceCost() {
        return serviceCost.get();
    }

    public DoubleProperty serviceCostProperty() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost.set(serviceCost);
    }

    public String getNote() {
        return note.get();
    }

    public StringProperty noteProperty() {
        return note;
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    public ObservableList<Service> getServices() {
        return services.get();
    }

    public ListProperty<Service> servicesProperty() {
        return services;
    }

    public void setServices(ObservableList<Service> services) {
        this.services.set(services);
    }
}
