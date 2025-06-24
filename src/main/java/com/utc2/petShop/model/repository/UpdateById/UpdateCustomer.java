package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCustomer {

    public static void updateCustomer(int customerId, String fullName,
                                      String phoneNumber) {

        String updateQuery = "UPDATE CUSTOMER SET customerName = ?, phoneNumber = ? " +
                "WHERE customerId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, fullName);
            stmt.setString(2, phoneNumber);
            stmt.setInt(3, customerId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập nhật khách hàng thành công.");
            } else {
                System.out.println("⚠️ Không tìm thấy khách hàng với customerId: " + customerId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật khách hàng: " + e.getMessage());
        }
    }
}



