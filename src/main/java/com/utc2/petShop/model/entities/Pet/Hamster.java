package com.utc2.petShop.model.entities.Pet;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Hamster extends Pet {
    private ObjectProperty<EHamsterBreed> breed;
    private DoubleProperty tailLength;

    public Hamster(int id, String name, int age, boolean gender, double price, boolean vaccinated, String healthStatus, String origin, double weight, String furColor, String description, int supplierID, EHamsterBreed breed, double tailLength) {
        super(id, name, age, gender, price, vaccinated, healthStatus, origin, weight, furColor, description, supplierID);
        this.breed = new SimpleObjectProperty<>(breed);
        this.tailLength = new SimpleDoubleProperty(tailLength);
    }

    public Hamster() {
        super();
        this.breed = new SimpleObjectProperty<EHamsterBreed>(EHamsterBreed.HamsterDuoiDai);
        this.tailLength = new SimpleDoubleProperty(0.0);
    }

    public EHamsterBreed getBreed() {
        return breed.get();
    }

    public ObjectProperty<EHamsterBreed> breedProperty() {
        return breed;
    }

    public void setBreed(EHamsterBreed breed) {
        this.breed.set(breed);
    }

    public double getTailLength() {
        return tailLength.get();
    }

    public DoubleProperty tailLengthProperty() {
        return tailLength;
    }

    public void setTailLength(double tailLength) {
        this.tailLength.set(tailLength);
    }

    @Override
    public String toString() {
        return "Hamster";
    }
}
