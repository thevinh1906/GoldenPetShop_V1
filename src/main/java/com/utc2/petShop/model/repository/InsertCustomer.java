package com.utc2.petShop.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertCustomer {

    public static void insertCustomer (String phoneNumber, String name) {
        String insertCustomer = "INSERT INTO CUSTOMER (phoneNumber,customerName) " +
                "VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertCustomer)) {

            stmt.setString(1, phoneNumber);
            stmt.setString(2, name);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Thêm người dùng thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

