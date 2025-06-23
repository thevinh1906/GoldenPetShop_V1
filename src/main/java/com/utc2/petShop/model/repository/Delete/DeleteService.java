package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteService {
    public static boolean deleteServiceById(int serviceId) {
        String sqlSoftDeleteService = "UPDATE Service SET isDeleted = 1 WHERE serviceId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlSoftDeleteService)) {

            stmt.setInt(1, serviceId);
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Không thể xoá mềm dịch vụ (Service) với ID = " + serviceId, e);
        }
    }
}
