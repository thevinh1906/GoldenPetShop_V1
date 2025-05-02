package com.utc2.petShop;

import com.utc2.petShop.model.entities.Pet.Pet;
import com.utc2.petShop.model.repository.SelectPet;

import java.sql.SQLException;
import java.util.List;

public class TestSelectPet {
    public static void main(String[] args) {
        try {
            List<Pet> pets = SelectPet.getAllPets();
            for (Pet pet : pets) {
                System.out.println(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
