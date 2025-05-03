package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.sql.*;

public class DeleteCustomer {
    private static Connection conn;

    public DeleteCustomer(Connection conn) {
        DeleteCustomer.conn = conn;
    }

    static {
        try {
            conn = DBConnection.getConnection();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteCustomerById(int customerId) throws SQLException {
        String sql = "DELETE FROM CUSTOMER WHERE customerId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
