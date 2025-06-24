package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateSupplier {

    public static void updateSupplier(int supplierId, String name, String email, String phoneNumber, String address) {
        String updateQuery = "UPDATE SUPPLIER SET supplierName = ?, email = ?, phone = ?, address = ? WHERE supplierId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phoneNumber);
            stmt.setString(4, address);
            stmt.setInt(5, supplierId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập nhật nhà cung cấp thành công.");
            } else {
                System.out.println("⚠️ Không tìm thấy nhà cung cấp với supplierId: " + supplierId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật nhà cung cấp: " + e.getMessage());
        }
    }
}



