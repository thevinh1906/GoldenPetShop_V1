package com.utc2.petShop.model.repository.Select;

import com.utc2.petShop.model.entities.Pet.*;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.entities.vaccine.Vaccine;
import com.utc2.petShop.utils.DBConnection;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectPet {

    public static List<Pet> getAllPets()  {
        List<Pet> pets = new ArrayList<>();
        String sql = """
                SELECT p.*, 
                       d.isTrained AS dog_isTrained, d.breed AS dog_breed,
                       c.isIndoor AS cat_isIndoor, c.eyeColor AS cat_eyeColor, c.breed AS cat_breed,
                       h.tailLength AS hamster_tailLength, h.breed AS hamster_breed,
                       r.earLength AS rabbit_earLength, r.breed AS rabbit_breed
                FROM PET p
                            LEFT JOIN Dog d ON p.petId = d.petId
                            LEFT JOIN Cat c ON p.petId = c.petId
                            LEFT JOIN Hamster h ON p.petId = h.petId
                            LEFT JOIN Rabbit r ON p.petId = r.petId
                WHERE p.isDeleted = 0
                """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("petId");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                boolean gender = rs.getBoolean("gender");
                double price = rs.getDouble("price");
                String healthStatus = rs.getString("healthStatus");
                String origin = rs.getString("origin");
                float weight = rs.getFloat("weight");
                String furColor = rs.getString("furColor");
                String description = rs.getString("description");
                int supplierId = rs.getInt("supplierId");
                byte[] image = rs.getBytes("image");
                List<Vaccine> vaccines = getVaccinesByPetId(id);


                Supplier supplier = SelectSupplier.getSupplierById(supplierId);

                Pet pet = null;

                if (rs.getObject("dog_isTrained") != null) {
                    boolean isTrained = rs.getBoolean("dog_isTrained");
                    String dogBreedStr = rs.getString("dog_breed");
                    EDogBreed dogBreed = null;
                    for (EDogBreed b : EDogBreed.values()) {
                        if (b.getBreed().equalsIgnoreCase(dogBreedStr.trim())) {
                            dogBreed = b;
                            break;
                        }
                    }
                    if (dogBreed == null) {
                        throw new IllegalArgumentException("Không tìm thấy giống chó phù hợp: " + dogBreedStr);
                    }

                    pet = new Dog(image, id, name, age, gender, price, vaccines, healthStatus, origin, weight, furColor, description, supplier, dogBreed, isTrained);

                } else if (rs.getObject("cat_isIndoor") != null) {
                    boolean isIndoor = rs.getBoolean("cat_isIndoor");
                    String eyeColor = rs.getString("cat_eyeColor");
                    String catBreedStr = rs.getString("cat_breed");
                    ECatBreed catBreed = null;
                    for (ECatBreed b : ECatBreed.values()) {
                        if (b.getBreed().equalsIgnoreCase(catBreedStr.trim())) {
                            catBreed = b;
                            break;
                        }
                    }
                    if (catBreed == null) {
                        throw new IllegalArgumentException("Không tìm thấy giống mèo phù hợp: " + catBreedStr);
                    }

                    pet = new Cat(image, id, name, age, gender, price, vaccines, healthStatus, origin, weight, furColor, description, supplier, catBreed, isIndoor, eyeColor);

                } else if (rs.getObject("hamster_tailLength") != null) {
                    float tailLength = rs.getFloat("hamster_tailLength");
                    String hamsterBreedStr = rs.getString("hamster_breed");
                    EHamsterBreed hamsterBreed = null;
                    for (EHamsterBreed b : EHamsterBreed.values()) {
                        if (b.getBreed().equalsIgnoreCase(hamsterBreedStr.trim())) {
                            hamsterBreed = b;
                            break;
                        }
                    }
                    if (hamsterBreed == null)
                        throw new IllegalArgumentException("Không tìm thấy giống hamster phù hợp: " + hamsterBreedStr);
                    pet = new Hamster(image, id, name, age, gender, price, vaccines, healthStatus, origin, weight, furColor, description, supplier, hamsterBreed, tailLength);


                } else if (rs.getObject("rabbit_earLength") != null) {
                    float earLength = rs.getFloat("rabbit_earLength");
                    String rabbitBreedStr = rs.getString("rabbit_breed");
                    ERabbitBreed rabbitBreed = null;
                    for (ERabbitBreed b : ERabbitBreed.values()) {
                        if (b.getBreed().equalsIgnoreCase(rabbitBreedStr.trim())) {
                            rabbitBreed = b;
                            break;
                        }
                    }
                    if (rabbitBreed == null)
                        throw new IllegalArgumentException("Không tìm thấy giống thỏ phù hợp: " + rabbitBreedStr);
                    pet = new Rabbit(image, id, name, age, gender, price, vaccines, healthStatus, origin, weight, furColor, description, supplier, rabbitBreed, earLength);

                } else {
                    pet = new Pet(image, id, name, age, gender, price, healthStatus, origin, weight, furColor, description, supplier, vaccines);
                }

                pets.add(pet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pets;
    }

    private static Image getDefaultImage() {
        try {
            return new Image(Pet.class.getResource("/images/KhongTimDuocAnh.jpg").toExternalForm());
        } catch (Exception e) {
            System.out.println("Không tìm thấy ảnh mặc định: " + e.getMessage());
        }
        return null;
    }

    public static List<Integer> getPetIDBySupplierId(int supplierId) {
        List<Integer> petIDs = new ArrayList<>();
        String sql = "SELECT petId FROM PET WHERE supplierId = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supplierId);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("petId");

                    petIDs.add(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return petIDs;
    }
    public static List<Vaccine> getVaccinesByPetId(int petId) {
        List<Vaccine> vaccines = new ArrayList<>();

        String sql = """
            SELECT V.* FROM VACCINE_PET VP
            JOIN VACCINE V ON VP.vaccineId = V.vaccineId
            WHERE VP.petId = ? AND V.isDeleted = 0
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, petId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vaccine vaccine = new Vaccine(
                            new SimpleIntegerProperty(rs.getInt("vaccineId")),
                            new SimpleStringProperty(rs.getString("vaccineName")),
                            new SimpleStringProperty(rs.getString("description")),
                            new SimpleStringProperty(rs.getString("applicableSpecies")),
                            new SimpleIntegerProperty(rs.getInt("doseCount")),
                            new SimpleIntegerProperty(rs.getInt("intervalDays")),
                            new SimpleIntegerProperty(rs.getInt("validityMonths")),
                            new SimpleBooleanProperty(rs.getBoolean("isMandatory"))
                    );

                    vaccines.add(vaccine);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Không thể lấy danh sách vaccine của petId = " + petId, e);
        }

        return vaccines;
    }









    public static List<Pet> getPetsByName(String keyword) {
        List<Pet> pets = new ArrayList<>();
        String sql = """
            SELECT p.*, 
                   d.isTrained AS dog_isTrained, d.breed AS dog_breed,
                   c.isIndoor AS cat_isIndoor, c.eyeColor AS cat_eyeColor, c.breed AS cat_breed,
                   h.tailLength AS hamster_tailLength, h.breed AS hamster_breed,
                   r.earLength AS rabbit_earLength, r.breed AS rabbit_breed
            FROM PET p
                     LEFT JOIN Dog d ON p.petId = d.petId
                     LEFT JOIN Cat c ON p.petId = c.petId
                     LEFT JOIN Hamster h ON p.petId = h.petId
                     LEFT JOIN Rabbit r ON p.petId = r.petId
            WHERE p.isDeleted = 0 AND LOWER(p.name) LIKE ?
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword.toLowerCase() + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("petId");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    boolean gender = rs.getBoolean("gender");
                    double price = rs.getDouble("price");
                    String healthStatus = rs.getString("healthStatus");
                    String origin = rs.getString("origin");
                    float weight = rs.getFloat("weight");
                    String furColor = rs.getString("furColor");
                    String description = rs.getString("description");
                    int supplierId = rs.getInt("supplierId");
                    byte[] image = rs.getBytes("image");
                    List<Vaccine> vaccines = getVaccinesByPetId(id);

                    Supplier supplier = SelectSupplier.getSupplierById(supplierId);
                    Pet pet = null;

                    if (rs.getObject("dog_isTrained") != null) {
                        boolean isTrained = rs.getBoolean("dog_isTrained");
                        String dogBreedStr = rs.getString("dog_breed");
                        EDogBreed dogBreed = null;
                        for (EDogBreed b : EDogBreed.values()) {
                            if (b.getBreed().equalsIgnoreCase(dogBreedStr.trim())) {
                                dogBreed = b;
                                break;
                            }
                        }
                        if (dogBreed == null) {
                            throw new IllegalArgumentException("Không tìm thấy giống chó phù hợp: " + dogBreedStr);
                        }
                        pet = new Dog(image, id, name, age, gender, price, vaccines, healthStatus, origin, weight, furColor, description, supplier, dogBreed, isTrained);

                    } else if (rs.getObject("cat_isIndoor") != null) {
                        boolean isIndoor = rs.getBoolean("cat_isIndoor");
                        String eyeColor = rs.getString("cat_eyeColor");
                        String catBreedStr = rs.getString("cat_breed");
                        ECatBreed catBreed = null;
                        for (ECatBreed b : ECatBreed.values()) {
                            if (b.getBreed().equalsIgnoreCase(catBreedStr.trim())) {
                                catBreed = b;
                                break;
                            }
                        }
                        if (catBreed == null) {
                            throw new IllegalArgumentException("Không tìm thấy giống mèo phù hợp: " + catBreedStr);
                        }
                        pet = new Cat(image, id, name, age, gender, price, vaccines, healthStatus, origin, weight, furColor, description, supplier, catBreed, isIndoor, eyeColor);

                    } else if (rs.getObject("hamster_tailLength") != null) {
                        float tailLength = rs.getFloat("hamster_tailLength");
                        String hamsterBreedStr = rs.getString("hamster_breed");
                        EHamsterBreed hamsterBreed = null;
                        for (EHamsterBreed b : EHamsterBreed.values()) {
                            if (b.getBreed().equalsIgnoreCase(hamsterBreedStr.trim())) {
                                hamsterBreed = b;
                                break;
                            }
                        }
                        if (hamsterBreed == null)
                            throw new IllegalArgumentException("Không tìm thấy giống hamster phù hợp: " + hamsterBreedStr);
                        pet = new Hamster(image, id, name, age, gender, price, vaccines, healthStatus, origin, weight, furColor, description, supplier, hamsterBreed, tailLength);

                    } else if (rs.getObject("rabbit_earLength") != null) {
                        float earLength = rs.getFloat("rabbit_earLength");
                        String rabbitBreedStr = rs.getString("rabbit_breed");
                        ERabbitBreed rabbitBreed = null;
                        for (ERabbitBreed b : ERabbitBreed.values()) {
                            if (b.getBreed().equalsIgnoreCase(rabbitBreedStr.trim())) {
                                rabbitBreed = b;
                                break;
                            }
                        }
                        if (rabbitBreed == null)
                            throw new IllegalArgumentException("Không tìm thấy giống thỏ phù hợp: " + rabbitBreedStr);
                        pet = new Rabbit(image, id, name, age, gender, price, vaccines, healthStatus, origin, weight, furColor, description, supplier, rabbitBreed, earLength);

                    } else {
                        pet = new Pet(image, id, name, age, gender, price, healthStatus, origin, weight, furColor, description, supplier, vaccines);
                    }

                    pets.add(pet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pets;
    }

}




