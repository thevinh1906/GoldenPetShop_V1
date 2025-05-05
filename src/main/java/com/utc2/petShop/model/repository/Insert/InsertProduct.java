package com.utc2.petShop.model.repository.Insert;

import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.repository.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class InsertProduct {

    public static void insertProduct (Supplier supplier, String name, double price, int quantity, String description,
                                       String manufacturer,String type, String brand,
                                       LocalDate expirationDate, String flavor, String dimension,
                                       String material, String size, String role) {
        int productId = 0;
        try (Connection conn = DBConnection.getConnection()) {
            String insertProduct = "INSERT INTO PRODUCTS (supplierId, name, price, quantity, description, manufacturer, role) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(insertProduct, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setInt(1, supplier.getId());
                stmt.setString(2, name);
                stmt.setDouble(3, price);
                stmt.setInt(4, quantity);
                stmt.setString(5, description);
                stmt.setString(6, manufacturer);
                stmt.setString(7, role);
                int a = stmt.executeUpdate();

                //lấy id product mới tăng
                if (a > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            productId = generatedKeys.getInt(1);
                            System.out.println("ID vừa được tạo: " + productId);
                        } else {
                            System.out.println("Không lấy được ID.");
                        }
                    }
                }
            }

            if (role.equalsIgnoreCase("Accessory")){
                String insertAccessory = "INSERT INTO Accessory (productId, type, brand) VALUES (?, ?, ?)";
                try (PreparedStatement stmtAccessory = conn.prepareStatement(insertAccessory)) {
                    stmtAccessory.setInt(1, productId);
                    stmtAccessory.setString(2, type);
                    stmtAccessory.setString(3, brand);
                    stmtAccessory.executeUpdate();
                }
            }
            else if (role.equalsIgnoreCase("Cage")){
                String insertCage = "INSERT INTO Cage (productId, dimension, material) VALUES (?, ?, ?)";
                try (PreparedStatement stmtCage = conn.prepareStatement(insertCage)) {
                    stmtCage.setInt(1, productId);
                    stmtCage.setString(2, dimension);
                    stmtCage.setString(3, material);
                    stmtCage.executeUpdate();
                }
            }
            else if (role.equalsIgnoreCase("Food")){
                String insertFood = "INSERT INTO Food (productId, expirationDate, flavor) VALUES (?, ?, ?)";
                try (PreparedStatement stmtFood = conn.prepareStatement(insertFood)) {
                    stmtFood.setInt(1, productId);
                    stmtFood.setDate(2, Date.valueOf(expirationDate));
                    stmtFood.setString(3, flavor);
                    stmtFood.executeUpdate();
                }
            } else if (role.equalsIgnoreCase("Toy")) {
                String insertToy = "INSERT INTO Toy (productId, material, size) VALUES (?, ?, ?)";
                try (PreparedStatement stmtToy = conn.prepareStatement(insertToy)) {
                    stmtToy.setInt(1, productId);
                    stmtToy.setString(2, material);
                    stmtToy.setString(3 , size);
                    stmtToy.executeUpdate();
                }
            }

            System.out.println("✅ Thêm thành công.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi thêm sản phẩm : " + e.getMessage());
        }
    }
}

