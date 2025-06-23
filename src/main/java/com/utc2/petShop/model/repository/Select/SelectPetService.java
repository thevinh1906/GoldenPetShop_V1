package com.utc2.petShop.model.repository.Select;

import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.entities.PetService.PetService;
import com.utc2.petShop.model.entities.Service.Service;
import com.utc2.petShop.model.repository.Select.SelectCustomer;
import com.utc2.petShop.utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SelectPetService {
    // Lấy tất cả PetServices chưa bị xóa
    public static List<PetService> getAllPetServices() {
        List<PetService> petServices = new ArrayList<>();
        String sql = "SELECT * FROM PetService WHERE isDeleted = 0"; // Truy vấn danh sách PetService chưa bị xoá

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PetService petService = new PetService();
                petService.setId(rs.getInt("petServiceId"));
                petService.setNamePet(rs.getString("namePet"));
                petService.setGender(rs.getString("gender"));
                petService.setAge(rs.getInt("age"));
                petService.setHealthStatus(rs.getString("healthStatus"));
                petService.setWeight(rs.getDouble("weight"));
                petService.setBreed(rs.getString("breed"));
                petService.setAnimal(rs.getString("animal"));
                petService.setDateOfVisit(rs.getDate("dateOfVisit").toLocalDate());
                petService.setStatus(rs.getString("status"));
                petService.setServiceCost(rs.getDouble("serviceCost"));
                petService.setNote(rs.getString("note"));

                petService.setServices(FXCollections.observableArrayList(getServicesByPetServiceId(petService.getId())));

                int customerId = rs.getInt("customerId");
                Customer customer = SelectCustomer.getCustomerById(customerId);
                petService.setCustomer(customer);

                petService.setServices(FXCollections.observableArrayList(new ArrayList<>()));

                petServices.add(petService);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return petServices;
    }
    // Lấy PetService theo ID khách hàng
    public static List<PetService> getPetServicesByCustomerId(int customerId) {
        List<PetService> petServices = new ArrayList<>();
        String sql = "SELECT * FROM PetService WHERE customerId = ? AND isDeleted = 0";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PetService petService = new PetService();
                    petService.setId(rs.getInt("petServiceId"));
                    petService.setNamePet(rs.getString("namePet"));
                    petService.setGender(rs.getString("gender"));
                    petService.setAge(rs.getInt("age"));
                    petService.setHealthStatus(rs.getString("healthStatus"));
                    petService.setWeight(rs.getDouble("weight"));
                    petService.setBreed(rs.getString("breed"));
                    petService.setAnimal(rs.getString("animal"));
                    petService.setDateOfVisit(rs.getDate("dateOfVisit").toLocalDate());
                    petService.setStatus(rs.getString("status"));
                    petService.setServiceCost(rs.getDouble("serviceCost"));
                    petService.setNote(rs.getString("note"));

                    Customer customer = SelectCustomer.getCustomerById(customerId);
                    petService.setCustomer(customer);

                    petService.setServices(FXCollections.observableArrayList(new ArrayList<>()));

                    petServices.add(petService);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return petServices;
    }
    // Truy xuất danh sách Service theo petServiceId
    private static List<Service> getServicesByPetServiceId(int petServiceId) {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT serviceId FROM PetService_Service WHERE petServiceId = ?"; // Truy vấn bảng liên kết PetService_Service để lấy ID dịch vụ

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, petServiceId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int serviceId = rs.getInt("serviceId");
                    Service service = SelectService.getServiceById(serviceId);
                    if (service != null) {
                        services.add(service);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Không thể lấy danh sách Service theo petServiceId = " + petServiceId, e);
        }

        return services;
    }
}
