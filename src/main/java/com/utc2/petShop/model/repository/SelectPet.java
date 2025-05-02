package com.utc2.petShop.model.repository;

import com.utc2.petShop.model.entities.Pet.*;
import com.utc2.petShop.model.entities.Supplier.Supplier;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectPet {
    private static Connection conn;
    private static SelectSupplier supplierRepo;

    public SelectPet(Connection conn) {
        this.conn = conn;
        this.supplierRepo = new SelectSupplier(conn);
    }
    static{
        try{
            conn = DBConnection.getConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Pet> getAllPets() throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String sql = """
            SELECT p.*, 
                   d.isTrained AS dog_isTrained,
                   c.isIndoor AS cat_isIndoor, c.eyeColor AS cat_eyeColor,
                   h.tailLength AS hamster_tailLength,
                   r.earLength AS rabbit_earLength
            FROM PET p
                        LEFT JOIN Dog d ON p.petId = d.petId
                        LEFT JOIN Cat c ON p.petId = c.petId
                        LEFT JOIN Hamster h ON p.petId = h.petId
                        LEFT JOIN Rabbit r ON p.petId = r.petId
            """;
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("petId");
                String name = rs.getString("name");
                String breed = rs.getString("breed");
                int age = rs.getInt("age");
                boolean gender = rs.getBoolean("gender");
                double price = rs.getDouble("price");
                boolean vaccinated = rs.getBoolean("vaccinated");
                String healthStatus = rs.getString("healthStatus");
                String origin = rs.getString("origin");
                float weight = rs.getFloat("weight");
                String furColor = rs.getString("furColor");
                String description = rs.getString("description");
                int supplierId = rs.getInt("supplierId");

                Supplier supplier = supplierRepo.getSupplierById(supplierId);

                Pet pet = null;

                if (rs.getObject("dog_isTrained") != null) {
                    boolean isTrained = rs.getBoolean("dog_isTrained");
                    String dogBreedStr = rs.getString("dog_breed");
                    EDogBreed dogBreed = null;
                    for (EDogBreed b : EDogBreed.values()) {
                        if (b.getBreed().equalsIgnoreCase(dogBreedStr)) {
                            dogBreed = b;
                            break;
                        }
                    }
                    if (dogBreed == null) {
                        throw new IllegalArgumentException("Không tìm thấy giống chó phù hợp: " + dogBreedStr);
                    }

                    pet = new Dog(id, name, age, gender, price, vaccinated, healthStatus, origin, weight, furColor, description, supplier, dogBreed, isTrained);

                } else if (rs.getObject("cat_isIndoor") != null) {
                    boolean isIndoor = rs.getBoolean("cat_isIndoor");
                    String eyeColor = rs.getString("cat_eyeColor");
                    String catBreedStr = rs.getString("cat_breed");
                    ECatBreed catBreed = null;
                    for (ECatBreed b : ECatBreed.values()) {
                        if (b.getBreed().equalsIgnoreCase(catBreedStr)) {
                            catBreed = b;
                            break;
                        }
                    }
                    if (catBreed == null) {
                        throw new IllegalArgumentException("Không tìm thấy giống mèo phù hợp: " + catBreedStr);
                    }

                    pet = new Cat(id, name, age, gender, price, vaccinated, healthStatus, origin, weight, furColor, description, supplier, catBreed, isIndoor, eyeColor);

                } else if (rs.getObject("hamster_tailLength") != null) {
                    float tailLength = rs.getFloat("hamster_tailLength");
                    String hamsterBreedStr = rs.getString("hamster_breed");
                    EHamsterBreed hamsterBreed = null;
                    for (EHamsterBreed b : EHamsterBreed.values()) {
                        if (b.getBreed().equalsIgnoreCase(hamsterBreedStr)) {
                            hamsterBreed = b;
                            break;
                        }
                    }
                    if (hamsterBreed == null) throw new IllegalArgumentException("Không tìm thấy giống hamster phù hợp: " + hamsterBreedStr);
                    pet = new Hamster(id, name, age, gender, price, vaccinated, healthStatus, origin, weight, furColor, description, supplier, hamsterBreed, tailLength);


                } else if (rs.getObject("rabbit_earLength") != null) {
                    float earLength = rs.getFloat("rabbit_earLength");
                    String rabbitBreedStr = rs.getString("rabbit_breed");
                    ERabbitBreed rabbitBreed = null;
                    for (ERabbitBreed b : ERabbitBreed.values()) {
                        if (b.getBreed().equalsIgnoreCase(rabbitBreedStr)) {
                            rabbitBreed = b;
                            break;
                        }
                    }
                    if (rabbitBreed == null) throw new IllegalArgumentException("Không tìm thấy giống thỏ phù hợp: " + rabbitBreedStr);
                    pet = new Rabbit(id, name, age, gender, price, vaccinated, healthStatus, origin, weight, furColor, description, supplier, rabbitBreed, earLength);

                } else {
                    pet = new Pet(id, name, age, gender, price, vaccinated, healthStatus, origin, weight, furColor, description, supplier);
                }

                pets.add(pet);
            }
        }

        return pets;
    }
}

