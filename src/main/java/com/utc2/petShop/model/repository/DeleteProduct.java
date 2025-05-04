package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.sql.*;

public class DeleteProduct {
    public static boolean deleteProductById(int productId) {
        String deleteAccessorySql = "DELETE FROM Accessory WHERE productId = ?";
        String deleteCageSql = "DELETE FROM Cage WHERE productId = ?";
        String deleteFoodSql = "DELETE FROM Food WHERE productId = ?";
        String deleteToySql = "DELETE FROM Toy WHERE productId = ?";
        String deleteProductSql = "DELETE FROM PRODUCTS WHERE productId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (
                    PreparedStatement stmtAccessory = conn.prepareStatement(deleteAccessorySql);
                    PreparedStatement stmtCage = conn.prepareStatement(deleteCageSql);
                    PreparedStatement stmtFood = conn.prepareStatement(deleteFoodSql);
                    PreparedStatement stmtToy = conn.prepareStatement(deleteToySql);
                    PreparedStatement stmtProduct = conn.prepareStatement(deleteProductSql)
            ) {
                // Xóa trong bảng con
                stmtAccessory.setInt(1, productId);
                stmtAccessory.executeUpdate();

                stmtCage.setInt(1, productId);
                stmtCage.executeUpdate();

                stmtFood.setInt(1, productId);
                stmtFood.executeUpdate();

                stmtToy.setInt(1, productId);
                stmtToy.executeUpdate();

                // Xóa bảng chính
                stmtProduct.setInt(1, productId);
                int rowsAffected = stmtProduct.executeUpdate();

                if (rowsAffected > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Lỗi khi xóa sản phẩm, rollback...", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kết nối CSDL khi xóa sản phẩm", e);
        }
    }
}
