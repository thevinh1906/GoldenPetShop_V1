package com.utc2.petShop.model.repository;

import com.utc2.petShop.model.entities.Supplier.Supplier;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class InsertProduct {

    public static void insertSupplier (int productId, Supplier supplier, String name, double price, int quantity, String description,
                                       String manufacturer,String type, String brand,
                                       Date expirationDate, String flavor, String dimension,
                                       String material, String size, String role) {
        try (Connection conn = DBConnection.getConnection()) {
            String insertProduct = "INSERT INTO SUPPLIER (productId, supplierId, name, price, quantity,description,manufacturer) " +
                "VALUES (?, ?, ?, ?, ?,?,?)";

            try (PreparedStatement stmt = conn.prepareStatement(insertProduct)) {

                stmt.setInt(1, productId);
                stmt.setString(2, name);
                stmt.setDouble(3, price);
                stmt.setInt(4, quantity);
                stmt.setString(5, description);
                stmt.setString(6, manufacturer);
                stmt.executeUpdate();
            }

            if (role.equalsIgnoreCase("Accessory")){
                String insertAccessory = "INSERT INTO Accessory (productId, type, brand) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertAccessory)) {
                    stmt.setInt(1, productId);
                    stmt.setString(2, type);
                    stmt.setString(3, brand);
                    stmt.executeUpdate();
                }
            }
            if (role.equalsIgnoreCase("Cage")){
                String insertCage = "INSERT INTO CAGE (productId, dimension, material) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertCage)) {
                    stmt.setInt(1, productId);
                    stmt.setString(2, dimension);
                    stmt.setString(3, material);
                    stmt.executeUpdate();
                }
            }
            if (role.equalsIgnoreCase("Food")){
                String insertFood = "INSERT INTO FOOD (productId, expirationDate, flavor) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertFood)) {
                    stmt.setInt(1, productId);
                    stmt.setDate(2, (java.sql.Date) expirationDate);
                    stmt.setString(3, flavor);
                    stmt.executeUpdate();
                }
            }

            System.out.println("✅ Thêm thành công.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi thêm sản phẩm : " + e.getMessage());
        }
    }
}

