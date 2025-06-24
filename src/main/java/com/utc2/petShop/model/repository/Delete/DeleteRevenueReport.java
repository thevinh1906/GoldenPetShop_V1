package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRevenueReport {
//Xóa báo cáo doanh thu theo id
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




