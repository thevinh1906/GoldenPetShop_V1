package com.utc2.petShop;

import com.utc2.petShop.model.entities.Pet.*;
import com.utc2.petShop.model.repository.DBConnection;
import com.utc2.petShop.model.repository.SelectPet;

import java.sql.Connection;
import java.util.List;

public class TestSelectPet {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Database connected successfully.");

            SelectPet sp = new SelectPet(conn);
            List<Pet> petList = sp.getAllPets();

            for (Pet pet : petList) {
                System.out.println("ID: " + pet.getId());
                System.out.println("Name: " + pet.getName());
                System.out.println("Age: " + pet.getAge());
                System.out.println("Gender: " + (pet.isGender() ? "Male" : "Female"));
                System.out.println("Price: " + pet.getPrice());
                System.out.println("Vaccinated: " + (pet.isVaccinated() ? "Yes" : "No"));
                System.out.println("Health Status: " + pet.getHealthStatus());
                System.out.println("Origin: " + pet.getOrigin());
                System.out.println("Weight: " + pet.getWeight());
                System.out.println("Fur Color: " + pet.getFurColor());
                System.out.println("Description: " + pet.getDescription());
                if (pet.getSupplier() != null) {
                    System.out.println("Supplier: " + pet.getSupplier().getName());
                } else {
                    System.out.println("Supplier: null");
                }

                // Thông tin riêng theo từng loại
                if (pet instanceof Dog dog) {
                    System.out.println("Breed: " + dog.getBreed().getBreed());
                    System.out.println("Is Trained: " + (dog.isIsTrained() ? "Yes" : "No"));
                } else if (pet instanceof Cat cat) {
                    System.out.println("Breed: " + cat.getBreed().getBreed());
                    System.out.println("Is Indoor: " + (cat.isIsIndoor() ? "Yes" : "No"));
                    System.out.println("Eye Color: " + cat.getEyeColor());
                } else if (pet instanceof Hamster hamster) {
                    System.out.println("Breed: " + hamster.getBreed().getBreed());
                    System.out.println("Tail Length: " + hamster.getTailLength());
                } else if (pet instanceof Rabbit rabbit) {
                    System.out.println("Breed: " + rabbit.getBreed().getBreed());
                    System.out.println("Ear Length: " + rabbit.getEarLength());
                }

                System.out.println("Class: " + pet.getClass().getSimpleName());
                System.out.println("------------");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
