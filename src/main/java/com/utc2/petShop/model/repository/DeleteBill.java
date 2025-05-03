package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.sql.*;

public class DeleteBill {
    private static Connection conn;

    public DeleteBill(Connection conn) {
        DeleteBill.conn = conn;
    }

    static {
        try {
            conn = DBConnection.getConnection();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteBillById(int billId) throws SQLException {
        String sql = "DELETE FROM BILL WHERE billId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
