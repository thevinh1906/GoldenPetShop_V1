package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.sql.*;

public class DeleteRevenueReport {

    public static boolean deleteRevenueReportById(int id) {
        String sql = "DELETE FROM REVENUE_REPORT WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa báo cáo doanh thu ID: " + id, e);
        }
    }
}

