package com.utc2.petShop.model.repository.Insert;

import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.entities.vaccine.Vaccine;
import com.utc2.petShop.model.entities.vaccine.Vaccine_Pet;
import com.utc2.petShop.utils.DBConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;

public class InsertPet {

    public static void insertPet(byte[] imageData, String name, int age, boolean gender, double price,
                                 List<Vaccine> vaccinated, String healthStatus, String origin, double weight,
                                 String furColor, String description, Supplier supplier,
                                 String role, Boolean isIndoor, String breed, String eyeColor,
                                 boolean isTrained, float tailLength, float earLength) {
        int id = 0;
        try (Connection conn = DBConnection.getConnection()) {
            String insertPet = "INSERT INTO PET (name, age, gender, price, healthStatus, origin, weight, furColor, description, supplierId, role, image) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertPet, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, name);
                stmt.setInt(2, age);
                stmt.setBoolean(3, gender);
                stmt.setDouble(4, price);
                stmt.setString(5, healthStatus);
                stmt.setString(6, origin);
                stmt.setDouble(7, weight);
                stmt.setString(8, furColor);
                stmt.setString(9, description);
                stmt.setInt(10, supplier.getId());
                stmt.setString(11, role);
                stmt.setBytes(12, imageData);


                int a = stmt.executeUpdate();

                //lấy id pet mới tăng
                if (a > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            id = generatedKeys.getInt(1);
                            System.out.println("ID vừa được tạo: " + id);
                            for(Vaccine pet : vaccinated) {
                                InsertVaccine_Pet.insertVaccinePet(id, pet.getVaccineId());
                            }
                        } else {
                            System.out.println("Không lấy được ID.");
                        }
                    }
                }

            }

            if (role.equalsIgnoreCase("Cat")) {
                String insertCat = "INSERT INTO Cat (petId, isIndoor, breed, eyeColor) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmtCat = conn.prepareStatement(insertCat)) {
                    stmtCat.setInt(1, id);
                    stmtCat.setBoolean(2, isIndoor);
                    stmtCat.setString(3, breed);
                    stmtCat.setString(4, eyeColor);
                    stmtCat.executeUpdate();
                }
            }
            else if (role.equalsIgnoreCase("Dog")) {
                String insertDog = "INSERT INTO Dog (petId, breed, isTrained) VALUES (?, ?, ?)";
                try (PreparedStatement stmtDog = conn.prepareStatement(insertDog)) {
                    stmtDog.setInt(1, id);
                    stmtDog.setString(2, breed);
                    stmtDog.setBoolean(3, isTrained);
                    stmtDog.executeUpdate();
                }
            }
            else if (role.equalsIgnoreCase("Hamster")) {
                String insertHamster = "INSERT INTO Hamster (petId, breed, tailLength) VALUES (?, ?, ?)";
                try (PreparedStatement stmtHamster = conn.prepareStatement(insertHamster)) {
                    stmtHamster.setInt(1, id);
                    stmtHamster.setString(2, breed);
                    stmtHamster.setDouble(3, tailLength);
                    stmtHamster.executeUpdate();
                }
            }
            else if (role.equalsIgnoreCase("Rabbit")) {
                String insertRabbit = "INSERT INTO Rabbit (petId, breed, earLength) VALUES (?, ?, ?)";
                try (PreparedStatement stmtRabbit = conn.prepareStatement(insertRabbit)) {
                    stmtRabbit.setInt(1, id);
                    stmtRabbit.setString(2, breed);
                    stmtRabbit.setDouble(3, earLength);
                    stmtRabbit.executeUpdate();
                }

            }

            System.out.println("✅ Thêm thú cưng thành công.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi thêm thú cưng: " + e.getMessage());
        }
    }


}



