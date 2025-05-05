package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.repository.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateProduct {

    public static void updateProduct(int productId, Supplier supplier, String name, double price, int quantity,
                                     String description, String manufacturer, String type, String brand,
                                     LocalDate expirationDate, String flavor, String dimension,
                                     String material, String size, String role) {

        String updateProduct = "UPDATE PRODUCTS SET supplierId = ?, name = ?, price = ?, quantity = ?, " +
                "description = ?, manufacturer = ? WHERE productId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateProduct)) {

            // Cập nhật bảng PRODUCTS
            stmt.setInt(1, supplier.getId());
            stmt.setString(2, name);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);
            stmt.setString(5, description);
            stmt.setString(6, manufacturer);
            stmt.setInt(7, productId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập nhật sản phẩm thành công.");
            } else {
                System.out.println("⚠️ Không tìm thấy sản phẩm với productId: " + productId);
                return;
            }

            // Cập nhật bảng phụ tùy theo role
            switch (role.toLowerCase()) {
                case "accessory":
                    String updateAccessory = "UPDATE Accessory SET type = ?, brand = ? WHERE productId = ?";
                    try (PreparedStatement accessoryStmt = conn.prepareStatement(updateAccessory)) {
                        accessoryStmt.setString(1, type);
                        accessoryStmt.setString(2, brand);
                        accessoryStmt.setInt(3, productId);
                        accessoryStmt.executeUpdate();
                    }
                    break;

                case "cage":
                    String updateCage = "UPDATE Cage SET dimension = ?, material = ? WHERE productId = ?";
                    try (PreparedStatement cageStmt = conn.prepareStatement(updateCage)) {
                        cageStmt.setString(1, dimension);
                        cageStmt.setString(2, material);
                        cageStmt.setInt(3, productId);
                        cageStmt.executeUpdate();
                    }
                    break;

                case "food":
                    String updateFood = "UPDATE Food SET expirationDate = ?, flavor = ? WHERE productId = ?";
                    try (PreparedStatement foodStmt = conn.prepareStatement(updateFood)) {
                        foodStmt.setDate(1, java.sql.Date.valueOf(expirationDate));
                        foodStmt.setString(2, flavor);
                        foodStmt.setInt(3, productId);
                        foodStmt.executeUpdate();
                    }
                    break;

                case "toy":
                    String updateToy = "UPDATE Toy SET material = ?, size = ? WHERE productId = ?";
                    try (PreparedStatement toyStmt = conn.prepareStatement(updateToy)) {
                        toyStmt.setString(1, material);
                        toyStmt.setString(2, size);
                        toyStmt.setInt(3, productId);
                        toyStmt.executeUpdate();
                    }
                    break;

                default:
                    System.out.println("⚠️ Không xác định được role phù hợp để cập nhật bảng phụ.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }
}
