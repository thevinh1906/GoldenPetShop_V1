package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.entities.PetService.PetService;
import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePetService {
    public static void updatePetService(PetService petService) {
        String updateQuery = "UPDATE PET_SERVICE SET namePet = ?, gender = ?, age = ?, customerId = ?, vaccineId = ?, " +
                "healthStatus = ?, weight = ?, breed = ?, animal = ?, dateOfVisit = ?, status = ?, " +
                "serviceCost = ?, note = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, petService.getNamePet());
            stmt.setString(2, petService.getGender());
            stmt.setInt(3, petService.getAge());
            stmt.setInt(4, petService.getCustomer().getId());
            stmt.setInt(5, petService.getVaccines().getVaccineId());
            stmt.setString(6, petService.getHealthStatus());
            stmt.setDouble(7, petService.getWeight());
            stmt.setString(8, petService.getBreed());
            stmt.setString(9, petService.getAnimal());
            stmt.setDate(10, Date.valueOf(petService.getDateOfVisit()));
            stmt.setString(11, petService.getStatus());
            stmt.setDouble(12, petService.getServiceCost());
            stmt.setString(13, petService.getNote());
            stmt.setInt(14, petService.getId());

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
}
