package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.sql.*;

public class DeleteSupplier {
    private static Connection conn;

    public DeleteSupplier(Connection conn) {
        DeleteSupplier.conn = conn;
    }

    static {
        try {
            conn = DBConnection.getConnection();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteSupplierById(int supplierId) throws SQLException {
        String sql = "DELETE FROM SUPPLIER WHERE supplierId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supplierId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
