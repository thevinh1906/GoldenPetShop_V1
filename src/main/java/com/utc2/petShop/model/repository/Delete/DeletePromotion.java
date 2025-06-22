package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePromotion {

    public static boolean deletePromotionById(int promotionId) {
        String sql = "DELETE FROM PROMOTION WHERE promotionId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, promotionId);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa khuyến mãi (ID: " + promotionId + ")", e);
        }
    }
}

