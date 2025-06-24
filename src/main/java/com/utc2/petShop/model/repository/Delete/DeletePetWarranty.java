package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePetWarranty {
//Xóa bảo hành theo pet id
    public static boolean deleteWarrantyByPetId(int petId) {
        String softDeleteSql = "UPDATE PET_WARRANTY SET isDeleted = 1 WHERE petId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(softDeleteSql)) {

            ps.setInt(1, petId);
            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xoá mềm bảo hành thú cưng (Pet_Warranty)", e);
        }
    }
}



