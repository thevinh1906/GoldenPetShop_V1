package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.sql.*;

public class DeletePromotion {
    private static Connection conn;

    public DeletePromotion(Connection conn) {
        DeletePromotion.conn = conn;
    }

    static {
        try {
            conn = DBConnection.getConnection();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deletePromotionById(int promotionId) throws SQLException {
        String sql = "DELETE FROM PROMOTION WHERE promotionId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, promotionId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
