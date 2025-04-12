package com.utc2.petShop.model.entities.Pet;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Rabbit extends Pet {
    private ObjectProperty<ERabbitBreed> breed;
    private DoubleProperty earLength;

    public Rabbit(int id, String name, int age, boolean gender, double price, boolean vaccinated, String healthStatus, String origin, double weight, String furColor, String description, int supplierID, ERabbitBreed breed, double earLength) {
        super(id, name, age, gender, price, vaccinated, healthStatus, origin, weight, furColor, description, supplierID);
        this.breed = new SimpleObjectProperty<>(ERabbitBreed.Angora);
        this.earLength = new SimpleDoubleProperty(earLength);
    }

    public Rabbit() {
        super();
        this.breed = new SimpleObjectProperty<ERabbitBreed>(ERabbitBreed.MiniRex);
        this.earLength = new SimpleDoubleProperty(0.0);
    }

    public ERabbitBreed getBreed() {
        return breed.get();
    }

    public ObjectProperty<ERabbitBreed> breedProperty() {
        return breed;
    }

    public void setBreed(ERabbitBreed breed) {
        this.breed.set(breed);
    }

    public double getEarLength() {
        return earLength.get();
    }

    public DoubleProperty earLengthProperty() {
        return earLength;
    }

    public void setEarLength(double earLength) {
        this.earLength.set(earLength);
    }

    @Override
    public String toString() {
        return "Rabbit";
    }
}
