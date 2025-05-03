package com.utc2.petShop.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Date;

public class InsertUser {
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=PetShopManagement;encrypt=true;trustServerCertificate=true";
    private static final String user = "sa";
    private static final String password = "123456";

    public static void insertUser(int id, String username, String password, String name, boolean gender, String email, String phoneNumber, String address, Date birthDay, Date creationDate) {
        String sql = "INSERT INTO USERS (userId, username, password, email, fullName, gender, phoneNumber, address, birthDay, creationDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Thêm thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

