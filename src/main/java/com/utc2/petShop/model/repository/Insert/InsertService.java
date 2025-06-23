package com.utc2.petShop.model.repository.Insert;

import com.utc2.petShop.model.entities.Service.Service;
import com.utc2.petShop.utils.DBConnection;

import java.sql.*;

public class InsertService {
    public static int insertService(Service service) {
        String sql = "INSERT INTO SERVICE (serviceName, description, price, applicableSpecies) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, service.getServiceName());
            stmt.setString(2, service.getDescription());
            stmt.setDouble(3, service.getPrice());
            stmt.setString(4, service.getApplicableSpecies());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        System.out.println("✅ ID của service vừa tạo: " + id);
                        return id;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Lỗi khi thêm service: " + e.getMessage(), e);
        }
        return 0;
    }
}
