package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.repository.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class UpdateCustomer {

    public static void updateCustomer(int customerId, String fullName, boolean gender, String email,
                                      String phoneNumber, String address, Date birthDate) {

        String updateQuery = "UPDATE CUSTOMER SET fullName = ?, gender = ?, email = ?, phoneNumber = ?, " +
                "address = ?, birthDate = ? WHERE customerId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, fullName);
            stmt.setBoolean(2, gender);
            stmt.setString(3, email);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, address);
            stmt.setDate(6, new java.sql.Date(birthDate.getTime()));
            stmt.setInt(7, customerId);

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
