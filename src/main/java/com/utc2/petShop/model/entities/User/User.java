package com.utc2.petShop.model.entities.User;

import com.utc2.petShop.model.implement.IBill;
import com.utc2.petShop.model.implement.IUser;
import javafx.beans.property.*;

import java.time.LocalDate;

// Represents a user in the system (customer, employee, manager)
public class User implements IUser {
    private IntegerProperty id;
    private StringProperty username;
    private StringProperty password;
    private StringProperty name;
    private BooleanProperty gender;
    private StringProperty email;
    private StringProperty phoneNumber;
    private StringProperty address;
    private ObjectProperty<LocalDate> birthDay;
    private ObjectProperty<LocalDate> creationDate;

    public User(int id, String username, String password, String name, boolean gender, String email, String phoneNumber, String address, LocalDate birthDay, LocalDate creationDate) {
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleBooleanProperty(gender);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.address = new SimpleStringProperty(address);
        this.birthDay = new SimpleObjectProperty<LocalDate>(birthDay);
        this.creationDate = new SimpleObjectProperty<LocalDate>(creationDate);
    }

    public User() {
        this.id = new SimpleIntegerProperty(0);
        this.username = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
        this.name = new SimpleStringProperty("");
        this.gender = new SimpleBooleanProperty(true);
        this.email = new SimpleStringProperty("");
        this.phoneNumber = new SimpleStringProperty("");
        this.address = new SimpleStringProperty("");
        this.birthDay = new SimpleObjectProperty<LocalDate>(LocalDate.now());
        this.creationDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
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

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
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

    public boolean isGender() {
        return gender.get();
    }

    public BooleanProperty genderProperty() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender.set(gender);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public LocalDate getBirthDay() {
        return birthDay.get();
    }

    public ObjectProperty<LocalDate> birthDayProperty() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay.set(birthDay);
    }

    public LocalDate getCreationDate() {
        return creationDate.get();
    }

    public ObjectProperty<LocalDate> creationDateProperty() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate.set(creationDate);
    }

    // phương thức cần thiết của User
    public void signIn() {

    }
    public void signUp() {

    }
    public void updateInfo() {

    }
    public void deleteAccount() {

    }

}
