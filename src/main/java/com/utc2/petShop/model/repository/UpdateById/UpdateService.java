package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.entities.Service.Service;
import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateService {
    public static void updateService(Service service) {
        String updateQuery = "UPDATE Service SET serviceName = ?, description = ?, price = ?, applicableSpecies = ? " +
                "WHERE serviceId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, service.getServiceName());
            stmt.setString(2, service.getDescription());
            stmt.setDouble(3, service.getPrice());
            stmt.setString(4, service.getApplicableSpecies());
            stmt.setInt(5, service.getServiceId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập nhật dịch vụ thành công.");
            } else {
                System.out.println("⚠️ Không tìm thấy dịch vụ với serviceId: " + service.getServiceId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật dịch vụ: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
