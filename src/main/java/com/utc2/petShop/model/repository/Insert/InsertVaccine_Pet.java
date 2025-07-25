package com.utc2.petShop.model.repository.Insert;

import com.utc2.petShop.model.entities.vaccine.Vaccine_Pet;
import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertVaccine_Pet {
    public static void insertVaccinePet(int petId, int vaccineId) {
        String sql = "INSERT INTO VACCINE_PET (petId, vaccineId) " +
                "VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, petId);
            stmt.setInt(2, vaccineId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Thêm bản ghi vaccine_pet thành công.");
            } else {
                System.out.println("⚠️ Không thêm được bản ghi vaccine_pet.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi thêm vào bảng VACCINE_PET: " + e.getMessage());
        }
    }
}



