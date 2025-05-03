package com.utc2.petShop.model.repository;

import com.utc2.petShop.model.entities.Supplier.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPet {
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=PetShopManagement;encrypt=true;trustServerCertificate=true";
    private static final String user = "sa";
    private static final String password = "123456";

    public static void insertPet(int id, String name, int age, boolean gender, double price,
                                 boolean vaccinated, String healthStatus, String origin, double weight,
                                 String furColor, String description, Supplier supplier,
                                 String role, Boolean isIndoor, String breed, String eyeColor,
                                 boolean isTrained, float tailLength, float earLength) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String insertPet = "INSERT INTO PET (petId, name, age, gender, price, vaccinated, healthStatus, origin, weight, furColor, description, supplierId) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertPet)) {
                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setInt(3, age);
                stmt.setBoolean(4, gender);
                stmt.setDouble(5, price);
                stmt.setBoolean(6, vaccinated);
                stmt.setString(7, healthStatus);
                stmt.setString(8, origin);
                stmt.setDouble(9, weight);
                stmt.setString(10, furColor);
                stmt.setString(11, description);
                stmt.setInt(12, supplier.getId());
                stmt.executeUpdate();
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
            if (role.equalsIgnoreCase("Dog")) {
                String insertDog = "INSERT INTO Dog (petId, breed, isTrained) VALUES (?, ?, ?)";
                try (PreparedStatement stmtDog = conn.prepareStatement(insertDog)) {
                    stmtDog.setInt(1, id);
                    stmtDog.setString(2, breed);
                    stmtDog.setBoolean(3, isTrained);
                    stmtDog.executeUpdate();
                }
            }
            if (role.equalsIgnoreCase("Hamster")) {
                String insertHamster = "INSERT INTO Hamster (petId, breed, tailLenght) VALUES (?, ?, ?)";
                try (PreparedStatement stmtHamster = conn.prepareStatement(insertHamster)) {
                    stmtHamster.setInt(1, id);
                    stmtHamster.setString(2, breed);
                    stmtHamster.setDouble(3, tailLength);
                    stmtHamster.executeUpdate();
                }
            }
            if (role.equalsIgnoreCase("Rabbit")) {
                String insertRabbit = "INSERT INTO Rabbit (petId, breed, earLengh) VALUES (?, ?, ?)";
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
