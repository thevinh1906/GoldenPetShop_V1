package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteVaccine {
    public static boolean deleteVaccineById(int vaccineId) {
        String sqlSoftDeleteVaccine = "UPDATE Vaccine SET isDeleted = 1 WHERE vaccineId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlSoftDeleteVaccine)) {

            stmt.setInt(1, vaccineId);
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Không thể xoá mềm vaccine với ID = " + vaccineId, e);
        }
    }
}
