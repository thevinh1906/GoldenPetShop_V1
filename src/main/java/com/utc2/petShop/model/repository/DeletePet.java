package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.sql.*;

public class DeletePet {
    private static Connection conn;

    public DeletePet(Connection conn) {
        this.conn = conn;
    }

    static {
        try {
            conn = DBConnection.getConnection();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deletePetById(int petId) throws SQLException {
        // Bắt đầu transaction
        conn.setAutoCommit(false);

        try {
            // Xóa các bản ghi liên quan ở các bảng con (Dog, Cat, Hamster, Rabbit)
            String deleteDog = "DELETE FROM Dog WHERE petId = ?";
            String deleteCat = "DELETE FROM Cat WHERE petId = ?";
            String deleteHamster = "DELETE FROM Hamster WHERE petId = ?";
            String deleteRabbit = "DELETE FROM Rabbit WHERE petId = ?";
            String deletePet = "DELETE FROM Pet WHERE petId = ?";
            String deletePetWarranty = "DELETE FROM PET_WARRANTY WHERE petId = ?";

            try (PreparedStatement psDog = conn.prepareStatement(deleteDog);
                 PreparedStatement psCat = conn.prepareStatement(deleteCat);
                 PreparedStatement psHamster = conn.prepareStatement(deleteHamster);
                 PreparedStatement psRabbit = conn.prepareStatement(deleteRabbit);
                 PreparedStatement psPet = conn.prepareStatement(deletePet);
                 PreparedStatement psPetWarranty = conn.prepareStatement(deletePetWarranty)) {

                // Xóa các bảng con trước (vì các bảng này có quan hệ phụ thuộc vào bảng Pet)
                psDog.setInt(1, petId);
                psDog.executeUpdate();

                psCat.setInt(1, petId);
                psCat.executeUpdate();

                psHamster.setInt(1, petId);
                psHamster.executeUpdate();

                psRabbit.setInt(1, petId);
                psRabbit.executeUpdate();

                psPetWarranty.setInt(1, petId);
                psPetWarranty.executeUpdate();

                // Cuối cùng, xóa bản ghi trong bảng Pet
                psPet.setInt(1, petId);
                psPet.executeUpdate();

                // Commit transaction
                conn.commit();
            } catch (SQLException e) {
                // Rollback nếu có lỗi
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Xóa thú cưng không thành công: " + e.getMessage(), e);
        } finally {
            // Đặt lại chế độ commit tự động cho các hoạt động sau này
            conn.setAutoCommit(true);
        }
    }
}
