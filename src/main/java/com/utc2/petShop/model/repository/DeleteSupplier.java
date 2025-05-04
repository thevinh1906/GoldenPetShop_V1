package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteSupplier {

    public static boolean deleteSupplierById(int supplierId) {
        String sql = "DELETE FROM SUPPLIER WHERE supplierId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // bắt đầu giao dịch

            try {

                List<Integer> petIds = new ArrayList<>();
                petIds = SelectPet.getPetIDBySupplierId(supplierId);

                for (Integer petId : petIds) {
                    DeletePet.deletePetById(petId);
                }

                List<Integer> productIds = new ArrayList<>();
                productIds = SelectProduct.getProductIDBySupplierId(supplierId);

                for (Integer productId : productIds) {
                    DeleteProduct.deleteProductById(productId);
                }

                // Xóa supplier
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, supplierId);
                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        conn.commit();
                        return true;
                    } else {
                        conn.rollback();
                        return false;
                    }
                }

            } catch (Exception e) {
                conn.rollback();
                throw new RuntimeException("Lỗi khi xóa supplier, rollback...", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Không thể kết nối hoặc lỗi SQL", e);
        }
    }
}