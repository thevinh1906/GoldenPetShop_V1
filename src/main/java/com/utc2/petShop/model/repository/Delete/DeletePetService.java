package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePetService {
//Xóa Chăm sóc thú cưng theo id
    public static boolean deletePetServiceById(int petServiceId) {
        String sql = "UPDATE PetService SET isDeleted = 1 WHERE petServiceId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, petServiceId);
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Không thể xoá mềm PetService với ID = " + petServiceId, e);
        }
    }
}



