package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePetWarranty {

    public static boolean deleteWarrantyByPetId(int petId) {
        String deleteWarrantySql = "DELETE FROM Pet_Warranty WHERE petId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // bắt đầu transaction

            try (PreparedStatement ps = conn.prepareStatement(deleteWarrantySql)) {
                ps.setInt(1, petId);
                int rows = ps.executeUpdate();

                if (rows > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Lỗi khi xóa bảo hành thú cưng", e);
            } finally {
                conn.setAutoCommit(true); // trả về mặc định
            }
        } catch (SQLException e) {
            throw new RuntimeException("Không thể kết nối CSDL hoặc lỗi SQL", e);
        }
    }
}
