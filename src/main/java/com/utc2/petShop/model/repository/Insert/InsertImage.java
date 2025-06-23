package com.utc2.petShop.model.repository.Insert;

import com.utc2.petShop.utils.DBConnection;

import java.sql.*;

public class InsertImage {
    public static int insertImage(byte[] image) {
        String sql = "INSERT INTO IMAGE(image) VALUES(?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setBytes(1, image);
            int row = ps.executeUpdate();

            if (row > 0) {
                try {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        System.out.println("ID của image vừa tạo: " + id);
                        return id;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return 0;
    }
}
