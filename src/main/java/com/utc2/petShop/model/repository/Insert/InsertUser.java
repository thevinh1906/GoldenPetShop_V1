package com.utc2.petShop.model.repository.Insert;

import com.utc2.petShop.utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class InsertUser {

    public static void insertUser(String username, String password, String name, boolean gender,
                                  String email, String phoneNumber, String address, LocalDate birthDay,
                                  LocalDate creationDate, String position, float salary,
                                  String workingHours, String role) {
        int id = 0;
        try (Connection conn = DBConnection.getConnection()) {
            String insertUser = "INSERT INTO USERS (username, password, fullName, gender, email, phoneNumber, address, createAt, birthDate, role)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)) {

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
                int a = stmt.executeUpdate();

                //lấy id user mới tăng
                if (a > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            id = generatedKeys.getInt(1);
                            System.out.println("ID vừa được tạo: " + id);
                        } else {
                            System.out.println("Không lấy được ID.");
                        }
                    }
                }
            }
            if(role.equals("Employee")) {
                String insertEmployee = "INSERT INTO EMPLOYEE (userId, position, salary, workingHours) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertEmployee)) {
                    stmt.setInt(1, id);
                    stmt.setString(2, position);
                    stmt.setFloat(3, salary);
                    stmt.setString(4, workingHours);
                    stmt.executeUpdate();
                }
            }
            // khoi tao sau
            // if(role.equalsIgnoreCase("Admin")) {}


                System.out.println("✅ Thêm thành công.");

        } catch (SQLException e) {
                 e.printStackTrace();
                 System.err.println("❌ Lỗi khi thêm user : " + e.getMessage());
             }
    }
}

