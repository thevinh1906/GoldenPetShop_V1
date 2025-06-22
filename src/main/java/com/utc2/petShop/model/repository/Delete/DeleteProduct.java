package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteProduct {
    public static boolean deleteProductById(int productId) {
        String deleteAccessorySql = "DELETE FROM Accessory WHERE productId = ?";
        String deleteCageSql = "DELETE FROM Cage WHERE productId = ?";
        String deleteFoodSql = "DELETE FROM Food WHERE productId = ?";
        String deleteToySql = "DELETE FROM Toy WHERE productId = ?";
        String softDeleteProductSql = "UPDATE PRODUCTS SET isDeleted = 1 WHERE productId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (
                    PreparedStatement stmtAccessory = conn.prepareStatement(deleteAccessorySql);
                    PreparedStatement stmtCage = conn.prepareStatement(deleteCageSql);
                    PreparedStatement stmtFood = conn.prepareStatement(deleteFoodSql);
                    PreparedStatement stmtToy = conn.prepareStatement(deleteToySql);
                    PreparedStatement stmtProduct = conn.prepareStatement(softDeleteProductSql)
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
                throw new RuntimeException("Lỗi khi xóa mềm sản phẩm, rollback...", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kết nối CSDL khi xóa sản phẩm", e);
        }
    }

    public static boolean deleteProductClassExtendsById(int productId) {
        String deleteAccessorySql = "DELETE FROM Accessory WHERE productId = ?";
        String deleteCageSql = "DELETE FROM Cage WHERE productId = ?";
        String deleteFoodSql = "DELETE FROM Food WHERE productId = ?";
        String deleteToySql = "DELETE FROM Toy WHERE productId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (
                    PreparedStatement stmtAccessory = conn.prepareStatement(deleteAccessorySql);
                    PreparedStatement stmtCage = conn.prepareStatement(deleteCageSql);
                    PreparedStatement stmtFood = conn.prepareStatement(deleteFoodSql);
                    PreparedStatement stmtToy = conn.prepareStatement(deleteToySql);
            ) {
                int rowsAffected = 0;
                // Xóa trong bảng con
                stmtAccessory.setInt(1, productId);
                rowsAffected += stmtAccessory.executeUpdate();

                stmtCage.setInt(1, productId);
                rowsAffected += stmtCage.executeUpdate();

                stmtFood.setInt(1, productId);
                rowsAffected += stmtFood.executeUpdate();

                stmtToy.setInt(1, productId);
                rowsAffected += stmtToy.executeUpdate();


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

    public static boolean deleteImageProductByProductIdAndImageId(int productId, int imageId) {
        String sqlDeleteImageProduct = "DELETE FROM IMAGE_PRODUCT WHERE imageId = ? AND productId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sqlDeleteImageProduct)) {
                stmt.setInt(1, imageId);
                stmt.setInt(2, productId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    conn.commit();
                    DeleteImage.deleteImageById(imageId);
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Lỗi khi xóa IMAGE_PRODUCT", e);
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
