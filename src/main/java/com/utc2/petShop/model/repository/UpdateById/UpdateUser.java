package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.repository.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class UpdateUser {

    public static void updateUser(int userId, String username, String password, String name, boolean gender,
                                  String email, String phoneNumber, String address, LocalDate birthDay,
                                  LocalDate creationDate, String position, float salary,
                                  String workingHours, String role) {

        try (Connection conn = DBConnection.getConnection()) {
            // Cập nhật bảng USERS
            String updateUser = "UPDATE USERS SET username = ?, password = ?, fullName = ?, gender = ?, email = ?, " +
                    "phoneNumber = ?, address = ?, createAt = ?, birthDate = ? WHERE userId = ?";

            try (PreparedStatement stmt = conn.prepareStatement(updateUser)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, name);
                stmt.setBoolean(4, gender);
                stmt.setString(5, email);
                stmt.setString(6, phoneNumber);
                stmt.setString(7, address);
                stmt.setDate(8, Date.valueOf(creationDate));
                stmt.setDate(9, Date.valueOf(birthDay));
                stmt.setInt(10, userId);

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("⚠️ Không tìm thấy người dùng với userId: " + userId);
                    return;
                }
            }

            // Nếu là nhân viên thì cập nhật bảng EMPLOYEE
            if (role.equalsIgnoreCase("Employee")) {
                String updateEmployee = "UPDATE EMPLOYEE SET position = ?, salary = ?, workingHours = ? WHERE userId = ?";
                try (PreparedStatement stmt = conn.prepareStatement(updateEmployee)) {
                    stmt.setString(1, position);
                    stmt.setFloat(2, salary);
                    stmt.setString(3, workingHours);
                    stmt.setInt(4, userId);
                    stmt.executeUpdate();
                }
            }

            System.out.println("✅ Cập nhật người dùng thành công.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật người dùng: " + e.getMessage());
        }
    }
}
