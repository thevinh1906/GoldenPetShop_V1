package com.utc2.petShop.model.entities.Pet;

import com.utc2.petShop.model.entities.Supplier.Supplier;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class Dog extends Pet {
    private ObjectProperty<EDogBreed> breed;
    private BooleanProperty isTrained;

    public Dog(Image image, int id, String name, int age, boolean gender, double price, boolean vaccinated, String healthStatus, String origin, double weight, String furColor, String description, Supplier supplier, EDogBreed breed, boolean isTrained) {
        super(image, id, name, age, gender, price, vaccinated, healthStatus, origin, weight, furColor, description, supplier);
        this.breed = new SimpleObjectProperty<>(breed);
        this.isTrained = new SimpleBooleanProperty(isTrained);
    }

    public Dog() {
        super();
        this.breed = new SimpleObjectProperty<EDogBreed>(EDogBreed.CauVang);
        this.isTrained = new SimpleBooleanProperty(true);
    }

    public EDogBreed getBreed() {
        return breed.get();
    }

    public ObjectProperty<EDogBreed> breedProperty() {
        return breed;
    }

    public void setBreed(EDogBreed breed) {
        this.breed.set(breed);
    }

    public boolean isIsTrained() {
        return isTrained.get();
    }

    public BooleanProperty isTrainedProperty() {
        return isTrained;
    }

    public void setIsTrained(boolean isTrained) {
        this.isTrained.set(isTrained);
    }

    @Override
    public String toString() {
        return "Dog";
    }
}