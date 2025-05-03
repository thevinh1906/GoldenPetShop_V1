package com.utc2.petShop.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertCustomer {
    private static final String url = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String user = "your_username";
    private static final String password = "your_password";

    public static void insertCustomer (int id, String phoneNumber, String name) {
        String sql = "INSERT INTO USERS (customerId, phoneNumber, customerName) " +
                "VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, phoneNumber);
            stmt.setString(3, name);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Thêm người dùng thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

