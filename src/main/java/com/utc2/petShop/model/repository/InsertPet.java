package com.utc2.petShop.model.repository;

import com.utc2.petShop.model.entities.Supplier.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Date;

public class InsertPet {
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=PetShopManagement;encrypt=true;trustServerCertificate=true";
    private static final String user = "sa";
    private static final String password = "123456";

    public static void insertPet (int id, String name, int age, boolean gender, double price, boolean vaccinated, String healthStatus, String origin, double weight, String furColor, String description, Supplier supplier) {
        String sql = "INSERT INTO PET (petId, name, breed, age, gender, price, vaccinated, healthStatus, origin, weight, furColor, description, supplierId) " +
                "VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
            stmt.setInt(12, id);


            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Thêm thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

