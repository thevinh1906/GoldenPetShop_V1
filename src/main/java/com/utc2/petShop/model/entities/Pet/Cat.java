package com.utc2.petShop.model.entities.Pet;

import javafx.beans.property.*;

public class Cat extends Pet {
    private BooleanProperty isIndoor;
    private ObjectProperty<ECatBreed> breed;
    private StringProperty eyeColor;

    public Cat(int id, String name, int age, boolean gender, double price, boolean vaccinated, String healthStatus, String origin, double weight, String furColor, String description, int supplierID, ECatBreed breed, boolean isIndoor, String eyeColor) {
        super(id, name, age, gender, price, vaccinated, healthStatus, origin, weight, furColor, description, supplierID);
        this.isIndoor = new SimpleBooleanProperty(isIndoor);
        this.breed = new SimpleObjectProperty<>(breed);
        this.eyeColor = new SimpleStringProperty(eyeColor);
    }

    public Cat() {
        super();
        this.isIndoor = new SimpleBooleanProperty(true);
        this.breed = new SimpleObjectProperty<ECatBreed>(ECatBreed.MeoCam);
        this.eyeColor = new SimpleStringProperty("");
    }

    public boolean isIsIndoor() {
        return isIndoor.get();
    }

    public BooleanProperty isIndoorProperty() {
        return isIndoor;
    }

    public void setIsIndoor(boolean isIndoor) {
        this.isIndoor.set(isIndoor);
    }

    public ECatBreed getBreed() {
        return breed.get();
    }

    public ObjectProperty<ECatBreed> breedProperty() {
        return breed;
    }

    public void setBreed(ECatBreed breed) {
        this.breed.set(breed);
    }

    public String getEyeColor() {
        return eyeColor.get();
    }

    public StringProperty eyeColorProperty() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor.set(eyeColor);
    }

    @Override
    public String toString() {
        return "Cat";
    }
}
