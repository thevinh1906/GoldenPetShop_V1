package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.entities.vaccine.Vaccine_Pet;
import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateVaccine_Pet {
    public static void updateVaccinePet(int petid, int vaccineid) throws SQLException {
        String sql = "UPDATE VACCINE_PET SET vaccineId = ? WHERE petId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vaccineid);
            stmt.setInt(2, petid);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập nhật thành công bản ghi VACCINE_PET.");
            } else {
                System.out.println("⚠️ Không tìm thấy bản ghi để cập nhật ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật VACCINE_PET: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}



