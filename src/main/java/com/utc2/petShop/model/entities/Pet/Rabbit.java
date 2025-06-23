package com.utc2.petShop.model.entities.Pet;

import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.entities.vaccine.Vaccine;
import com.utc2.petShop.model.entities.vaccine.Vaccine_Pet;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.util.List;

public class Rabbit extends Pet {
    private final ObjectProperty<ERabbitBreed> breed;
    private final DoubleProperty earLength;

    public Rabbit(byte[] image, int id, String name, int age, boolean gender, double price, List<Vaccine> vaccinated, String healthStatus, String origin, double weight, String furColor, String description, Supplier supplier, ERabbitBreed breed, double earLength) {
        super(image, id, name, age, gender, price, healthStatus, origin, weight, furColor, description, supplier,vaccinated);
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
