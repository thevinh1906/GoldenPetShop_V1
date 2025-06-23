package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.entities.Image.ImageByte;
import com.utc2.petShop.utils.DBConnection;
import com.utc2.petShop.model.repository.Delete.DeleteUser;

import java.sql.*;
import java.time.LocalDate;

public class UpdateUser {

    public static void updateUser(int userId, String username, String password, String name, boolean gender,
                                  String email, String phoneNumber, String address, LocalDate birthDay,
                                  LocalDate creationDate, String position, float salary,
                                  String workingHours, String role, ImageByte image) {

        try (Connection conn = DBConnection.getConnection()) {
            // Cập nhật bảng USERS
            String updateUser = "UPDATE USERS SET username = ?, password = ?, fullName = ?, gender = ?, email = ?, " +
                    "phoneNumber = ?, address = ?, createAt = ?, birthDate = ?, role = ?, imageId = ? WHERE userId = ?";

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
                stmt.setString(10, role);
                stmt.setInt(11, image.getId());
                stmt.setInt(12, userId);

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("⚠️ Không tìm thấy người dùng với userId: " + userId);
                    return;
                }
            }

            DeleteUser.deleteUserClassExtendsById(userId);

            // Nếu là nhân viên thì cập nhật bảng EMPLOYEE
            if (role.equalsIgnoreCase("Employee")) {
                String insertEmployee = "INSERT INTO EMPLOYEE (userId, position, salary, workingHours) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertEmployee)) {
                    stmt.setInt(1, userId);
                    stmt.setString(2, position);
                    stmt.setFloat(3, salary);
                    stmt.setString(4, workingHours);
                    stmt.executeUpdate();
                }
            }

            UpdateImage.updateImage(image);

            System.out.println("✅ Cập nhật người dùng thành công.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật người dùng: " + e.getMessage());
        }
    }
}
