package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.model.repository.*;
import com.utc2.petShop.model.repository.Select.SelectPet;
import com.utc2.petShop.model.repository.Select.SelectProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteSupplier {

    public static boolean deleteSupplierById(int supplierId) {
        String sqlSoftDeleteSupplier = "UPDATE SUPPLIER SET isDeleted = 1 WHERE customerId = ?";

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
                try (PreparedStatement stmt = conn.prepareStatement(sqlSoftDeleteSupplier)) {
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