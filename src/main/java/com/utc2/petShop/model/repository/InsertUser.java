package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class InsertUser {

    public static void insertUser(int id, String username, String password, String name, boolean gender,
                                  String email, String phoneNumber, String address, Date birthDay,
                                  Date creationDate, String position, float salary,
                                  String workingHours, String role) {
        try (Connection conn = DBConnection.getConnection()) {
            String insertUser = "INSERT INTO USERS (userId, username, password, email, fullName, gender, phoneNumber, address, birthDay, creationDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(insertUser)) {

                stmt.setInt(1, id);
                stmt.setString(2, username);
                stmt.setString(3, password);
                stmt.setString(4, name);
                stmt.setBoolean(5, gender);
                stmt.setString(6, email);
                stmt.setString(7, phoneNumber);
                stmt.setString(8, address);
                stmt.setDate(9, (java.sql.Date) birthDay);
                stmt.setDate(10, (java.sql.Date) creationDate);
                stmt.executeUpdate();
            }
            if(role.equalsIgnoreCase("Employee")) {
                String insertEmployee = "INSERT INTO EMPLOYEE (userId, position, salary, workingHours) VALUES (?, ?, ?,?)";
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

        } catch (SQLException | IOException e) {
                 e.printStackTrace();
                 System.err.println("❌ Lỗi khi thêm user : " + e.getMessage());
             }
    }
}

