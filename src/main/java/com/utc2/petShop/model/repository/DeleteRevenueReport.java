package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.sql.*;

public class DeleteRevenueReport {
    private static Connection conn;

    public DeleteRevenueReport(Connection conn) {
        DeleteRevenueReport.conn = conn;
    }

    static {
        try {
            conn = DBConnection.getConnection();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteRevenueReportById(int id) throws SQLException {
        String sql = "DELETE FROM REVENUE_REPORT WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
