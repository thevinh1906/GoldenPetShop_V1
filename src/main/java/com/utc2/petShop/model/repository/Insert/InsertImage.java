package com.utc2.petShop.model.repository.Insert;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertImage {
    public static void insertImage(byte[] image) {
        String sql = "INSERT INTO IMAGE(image) VALUES(?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBytes(1, image);
            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Inserted image successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
