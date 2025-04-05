package com.utc2.petShop.model.entities.Pet;

// Represents a pet in the store
public class Pet {
    private int petId;
    private String name;
    private String species;
    private String breed;
    private int age;
    private String gender;
    private double price;
    private boolean vaccinated;
    private String healthStatus;

    // Retrieves basic pet information
    public String getInfo() {
        return name + " (" + species + ") - " + healthStatus;
    }

    // Updates pet details
    public void updateInfo(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Checks if the pet is vaccinated
    public boolean checkVaccination() {
        return vaccinated;
    }
}
