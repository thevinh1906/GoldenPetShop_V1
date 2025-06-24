package com.utc2.petShop.model.repository.Select;

import com.utc2.petShop.model.entities.Service.Service;
import com.utc2.petShop.utils.DBConnection;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectService {
    public static List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM Service WHERE isDeleted = 0";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Service service = new Service();
                service.setServiceId(rs.getInt("serviceId"));
                service.setServiceName(rs.getString("serviceName"));
                service.setDescription(rs.getString("description"));
                service.setPrice(rs.getDouble("price"));
                service.setApplicableSpecies(rs.getString("applicableSpecies"));

                services.add(service);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return services;
    }

    public static Service getServiceById(int serviceId) {
        String sql = "SELECT * FROM Service WHERE serviceId = ? AND isDeleted = 0";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, serviceId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Service service = new Service();
                    service.setServiceId(rs.getInt("serviceId"));
                    service.setServiceName(rs.getString("serviceName"));
                    service.setDescription(rs.getString("description"));
                    service.setPrice(rs.getDouble("price"));
                    service.setApplicableSpecies(rs.getString("applicableSpecies"));
                    return service;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Không thể lấy service với ID = " + serviceId, e);
        }

        return null;
    }




    public static List<Service> getServicesByName(String keyword) {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM Service WHERE isDeleted = 0 AND LOWER(serviceName) LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword.toLowerCase() + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Service service = new Service();
                    service.setServiceId(rs.getInt("serviceId"));
                    service.setServiceName(rs.getString("serviceName"));
                    service.setDescription(rs.getString("description"));
                    service.setPrice(rs.getDouble("price"));
                    service.setApplicableSpecies(rs.getString("applicableSpecies"));

                    services.add(service);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm dịch vụ theo tên: " + keyword, e);
        }

        return services;
    }

}