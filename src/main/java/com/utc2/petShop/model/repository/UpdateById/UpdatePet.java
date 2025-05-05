package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.repository.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePet {

    public static void updatePet(int petId, String name, int age, boolean gender, double price,
                                 boolean vaccinated, String healthStatus, String origin, double weight,
                                 String furColor, String description, Supplier supplier,
                                 String role, Boolean isIndoor, String breed, String eyeColor,
                                 boolean isTrained, float tailLength, float earLength) {
        try (Connection conn = DBConnection.getConnection()) {
            // Cập nhật bảng PET
            String updatePet = "UPDATE PET SET name = ?, age = ?, gender = ?, price = ?, vaccinated = ?, " +
                    "healthStatus = ?, origin = ?, weight = ?, furColor = ?, description = ?, supplierId = ? " +
                    "WHERE petId = ?";

            try (PreparedStatement stmt = conn.prepareStatement(updatePet)) {
                stmt.setString(1, name);
                stmt.setInt(2, age);
                stmt.setBoolean(3, gender);
                stmt.setDouble(4, price);
                stmt.setBoolean(5, vaccinated);
                stmt.setString(6, healthStatus);
                stmt.setString(7, origin);
                stmt.setDouble(8, weight);
                stmt.setString(9, furColor);
                stmt.setString(10, description);
                stmt.setInt(11, supplier.getId());
                stmt.setInt(12, petId);
                int affected = stmt.executeUpdate();

                if (affected == 0) {
                    System.out.println("⚠️ Không tìm thấy thú cưng với petId: " + petId);
                    return;
                }
            }

            // Cập nhật bảng phụ tương ứng theo role
            switch (role.toLowerCase()) {
                case "cat":
                    String updateCat = "UPDATE Cat SET isIndoor = ?, breed = ?, eyeColor = ? WHERE petId = ?";
                    try (PreparedStatement stmtCat = conn.prepareStatement(updateCat)) {
                        stmtCat.setBoolean(1, isIndoor);
                        stmtCat.setString(2, breed);
                        stmtCat.setString(3, eyeColor);
                        stmtCat.setInt(4, petId);
                        stmtCat.executeUpdate();
                    }
                    break;

                case "dog":
                    String updateDog = "UPDATE Dog SET breed = ?, isTrained = ? WHERE petId = ?";
                    try (PreparedStatement stmtDog = conn.prepareStatement(updateDog)) {
                        stmtDog.setString(1, breed);
                        stmtDog.setBoolean(2, isTrained);
                        stmtDog.setInt(3, petId);
                        stmtDog.executeUpdate();
                    }
                    break;

                case "hamster":
                    String updateHamster = "UPDATE Hamster SET breed = ?, tailLength = ? WHERE petId = ?";
                    try (PreparedStatement stmtHamster = conn.prepareStatement(updateHamster)) {
                        stmtHamster.setString(1, breed);
                        stmtHamster.setFloat(2, tailLength);
                        stmtHamster.setInt(3, petId);
                        stmtHamster.executeUpdate();
                    }
                    break;

                case "rabbit":
                    String updateRabbit = "UPDATE Rabbit SET breed = ?, earLength = ? WHERE petId = ?";
                    try (PreparedStatement stmtRabbit = conn.prepareStatement(updateRabbit)) {
                        stmtRabbit.setString(1, breed);
                        stmtRabbit.setFloat(2, earLength);
                        stmtRabbit.setInt(3, petId);
                        stmtRabbit.executeUpdate();
                    }
                    break;

                default:
                    System.out.println("⚠️ Role không hợp lệ hoặc chưa được hỗ trợ.");
                    return;
            }

            System.out.println("✅ Cập nhật thú cưng thành công.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật thú cưng: " + e.getMessage());
        }
    }
}
