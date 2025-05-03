package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.sql.*;

public class DeletePetWarranty {
    private static Connection conn;

    public DeletePetWarranty(Connection conn) {
        this.conn = conn;
    }

    static {
        try {
            conn = DBConnection.getConnection();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteWarrantyByPetId(int petId) throws SQLException {
        conn.setAutoCommit(false);

        try {
            String deleteWarrantySql = "DELETE FROM Pet_Warranty WHERE petId = ?";

            try (PreparedStatement ps = conn.prepareStatement(deleteWarrantySql)) {
                ps.setInt(1, petId);
                ps.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Xóa bảo hành của thú cưng không thành công: " + e.getMessage(), e);
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
