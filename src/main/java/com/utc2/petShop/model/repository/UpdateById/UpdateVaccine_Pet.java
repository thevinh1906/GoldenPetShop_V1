package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.entities.vaccine.Vaccine_Pet;
import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateVaccine_Pet {
    public static void updateVaccinePet(Vaccine_Pet record) {
        String sql = "UPDATE VACCINE_PET SET vaccinationDate = ?, doseNumber = ? " +
                "WHERE petId = ? AND vaccineId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(record.getVaccinationDate()));
            stmt.setInt(2, record.getDoseNumber());
            stmt.setInt(3, record.getPetId());
            stmt.setInt(4, record.getVaccineId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập nhật thành công bản ghi VACCINE_PET.");
            } else {
                System.out.println("⚠️ Không tìm thấy bản ghi để cập nhật (petId: " + record.getPetId()
                        + ", vaccineId: " + record.getVaccineId() + ").");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật VACCINE_PET: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
