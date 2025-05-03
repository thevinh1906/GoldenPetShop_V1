package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.sql.*;

public class DeleteProduct {
    private static Connection conn;

    public DeleteProduct(Connection conn) {
        DeleteProduct.conn = conn;
    }

    static {
        try {
            conn = DBConnection.getConnection();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteProductById(int productId) throws SQLException {
        try {
            conn.setAutoCommit(false);

            // Xóa các liên kết trong các bảng con trước khi xóa từ bảng chính
            String deleteAccessorySql = "DELETE FROM Accessory WHERE productId = ?";
            String deleteCageSql = "DELETE FROM Cage WHERE productId = ?";
            String deleteFoodSql = "DELETE FROM Food WHERE productId = ?";
            String deleteToySql = "DELETE FROM Toy WHERE productId = ?";

            try (PreparedStatement stmt = conn.prepareStatement(deleteAccessorySql)) {
                stmt.setInt(1, productId);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(deleteCageSql)) {
                stmt.setInt(1, productId);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(deleteFoodSql)) {
                stmt.setInt(1, productId);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(deleteToySql)) {
                stmt.setInt(1, productId);
                stmt.executeUpdate();
            }

            // Xóa bản ghi trong bảng PRODUCTS
            String deleteProductSql = "DELETE FROM PRODUCTS WHERE productId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteProductSql)) {
                stmt.setInt(1, productId);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            }
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
