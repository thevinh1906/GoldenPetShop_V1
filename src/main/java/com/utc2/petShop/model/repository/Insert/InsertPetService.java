package com.utc2.petShop.model.repository.Insert;

import com.utc2.petShop.model.entities.PetService.PetService;
import com.utc2.petShop.model.entities.Service.Service;
import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class InsertPetService {
    public static void insertPetService(PetService petService) {
        String sql = "INSERT INTO PetService (namePet, gender, age, customerId, vaccineId, healthStatus, weight, " +
                "breed, animal, dateOfVisit, status, serviceCost, note) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, petService.getNamePet());
            stmt.setString(2, petService.getGender());
            stmt.setInt(3, petService.getAge());
            stmt.setInt(4, petService.getCustomer().getId());      // FK: customerId
            stmt.setInt(5, petService.getVaccines().getVaccineId());      // FK: vaccineId
            stmt.setString(6, petService.getHealthStatus());
            stmt.setDouble(7, petService.getWeight());
            stmt.setString(8, petService.getBreed());
            stmt.setString(9, petService.getAnimal());
            stmt.setDate(10, Date.valueOf(petService.getDateOfVisit()));
            stmt.setString(11, petService.getStatus());
            stmt.setDouble(12, petService.getServiceCost());
            stmt.setString(13, petService.getNote());

            for (Service service : petService.getServices()) {
                insertPetService_Service(petService.getId(), service.getServiceId());
            }


            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Thêm thông tin tiêm chủng thú cưng thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertPetService_Service(int petServiceId, int serviceId) {
        String sql = "INSERT INTO PetService_Service(petServiceId, serviceId" + "VALUES(?,?)";

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



