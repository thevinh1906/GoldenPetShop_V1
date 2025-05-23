package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePet {

    public static boolean deletePetById(int petId) {
        String deleteDog = "DELETE FROM Dog WHERE petId = ?";
        String deleteCat = "DELETE FROM Cat WHERE petId = ?";
        String deleteHamster = "DELETE FROM Hamster WHERE petId = ?";
        String deleteRabbit = "DELETE FROM Rabbit WHERE petId = ?";
        String deletePetWarranty = "DELETE FROM PET_WARRANTY WHERE petId = ?";
        String deletePet = "DELETE FROM Pet WHERE petId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // bắt đầu giao dịch

            try (
                    PreparedStatement psDog = conn.prepareStatement(deleteDog);
                    PreparedStatement psCat = conn.prepareStatement(deleteCat);
                    PreparedStatement psHamster = conn.prepareStatement(deleteHamster);
                    PreparedStatement psRabbit = conn.prepareStatement(deleteRabbit);
                    PreparedStatement psWarranty = conn.prepareStatement(deletePetWarranty);
                    PreparedStatement psPet = conn.prepareStatement(deletePet)
            ) {
                // Xóa lần lượt các bảng con
                psDog.setInt(1, petId); psDog.executeUpdate();
                psCat.setInt(1, petId); psCat.executeUpdate();
                psHamster.setInt(1, petId); psHamster.executeUpdate();
                psRabbit.setInt(1, petId); psRabbit.executeUpdate();
                psWarranty.setInt(1, petId); psWarranty.executeUpdate();

                // Cuối cùng xóa Pet
                psPet.setInt(1, petId);
                int affected = psPet.executeUpdate();

                if (affected > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }

            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Lỗi khi xóa thú cưng: " + e.getMessage(), e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Không thể kết nối hoặc lỗi SQL", e);
        }
    }

    public static boolean deletePetClassExtendsById(int petId) {
        String deleteDog = "DELETE FROM Dog WHERE petId = ?";
        String deleteCat = "DELETE FROM Cat WHERE petId = ?";
        String deleteHamster = "DELETE FROM Hamster WHERE petId = ?";
        String deleteRabbit = "DELETE FROM Rabbit WHERE petId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // bắt đầu giao dịch

            try (
                    PreparedStatement psDog = conn.prepareStatement(deleteDog);
                    PreparedStatement psCat = conn.prepareStatement(deleteCat);
                    PreparedStatement psHamster = conn.prepareStatement(deleteHamster);
                    PreparedStatement psRabbit = conn.prepareStatement(deleteRabbit);
            ) {

                int affected = 0;
                // Xóa lần lượt các bảng con
                psDog.setInt(1, petId);
                affected += psDog.executeUpdate();

                psCat.setInt(1, petId);
                affected += psCat.executeUpdate();

                psHamster.setInt(1, petId);
                affected += psHamster.executeUpdate();

                psRabbit.setInt(1, petId);
                affected += psRabbit.executeUpdate();

                if (affected > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }

            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Lỗi khi xóa thú cưng: " + e.getMessage(), e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Không thể kết nối hoặc lỗi SQL", e);
        }
    }
}
