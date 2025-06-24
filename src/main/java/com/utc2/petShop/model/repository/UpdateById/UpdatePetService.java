package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.entities.Pet.Pet;
import com.utc2.petShop.model.entities.PetService.PetService;
import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePetService {
    public static void updatePetService(PetService petService) {
        String updateQuery = "UPDATE PetService SET namePet = ?, gender = ?, age = ?, customerId = ?, " +
                "healthStatus = ?, weight = ?, breed = ?, animal = ?, dateOfVisit = ?, status = ?, " +
                "serviceCost = ?, note = ? WHERE petServiceId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, petService.getNamePet());
            stmt.setString(2, petService.getGender());
            stmt.setInt(3, petService.getAge());
            stmt.setInt(4, petService.getCustomer().getId());
            stmt.setString(5, petService.getHealthStatus());
            stmt.setDouble(6, petService.getWeight());
            stmt.setString(7, petService.getBreed());
            stmt.setString(8, petService.getAnimal());
            stmt.setDate(9, Date.valueOf(petService.getDateOfVisit()));
            stmt.setString(10, petService.getStatus());
            stmt.setDouble(11, petService.getServiceCost());
            stmt.setString(12, petService.getNote());
            stmt.setInt(13, petService.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập nhật thông tin PetService thành công.");
            } else {
                System.out.println("⚠️ Không tìm thấy bản ghi PetService với id: " + petService.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật PetService: " + e.getMessage());
        }
    }

    public static void UpdatePetService_Service(int petServiceId,int serviceId) {
        String sql = "UPDATE PetService_Service SET serviceId = ? WHERE serviceId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setInt(1, petServiceId);
            stmt.setInt(2, serviceId);


            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Thêm thông tin tiêm chủng thú cưng thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



