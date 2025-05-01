package com.utc2.petShop;

import com.utc2.petShop.model.repository.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestQuery {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT userId, username, email FROM USERS";

            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Danh sách người dùng:");
            while (rs.next()) {
                int id = rs.getInt("userId");
                String username = rs.getString("username");
                String email = rs.getString("email");

                System.out.printf("ID: %d | Username: %s | Email: %s%n", id, username, email);
            }

            rs.close();
        } catch (Exception e) {
            System.err.println("Lỗi khi truy vấn dữ liệu:");
            e.printStackTrace();
        }
    }
}
