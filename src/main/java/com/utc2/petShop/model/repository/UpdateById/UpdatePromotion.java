package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class UpdatePromotion {

    public static void updatePromotion(int promotionId, String name, float discountPercentage,
                                       LocalDate startDate, LocalDate endDate, Boolean isActive) {
        String updateQuery = "UPDATE PROMOTION SET name = ?, discountPercentage = ?, startDate = ?, endDate = ?, isActive = ? " +
                "WHERE promotionId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, name);
            stmt.setFloat(2, discountPercentage);
            stmt.setDate(3, Date.valueOf(startDate));
            stmt.setDate(4, Date.valueOf(endDate));
            stmt.setBoolean(5, isActive);
            stmt.setInt(6, promotionId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập nhật khuyến mãi thành công.");
            } else {
                System.out.println("⚠️ Không tìm thấy khuyến mãi với promotionId: " + promotionId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật khuyến mãi: " + e.getMessage());
        }
    }
}



