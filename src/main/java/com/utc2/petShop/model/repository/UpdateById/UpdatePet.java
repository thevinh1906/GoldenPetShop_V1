package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.utils.DBConnection;
import com.utc2.petShop.model.repository.Delete.DeletePet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePet {

    public static void updatePet(byte[] imageData, int petId, String name, int age, boolean gender, double price,
                                 boolean vaccinated, String healthStatus, String origin, double weight,
                                 String furColor, String description, Supplier supplier,
                                 String role, Boolean isIndoor, String breed, String eyeColor,
                                 boolean isTrained, float tailLength, float earLength) {
        try (Connection conn = DBConnection.getConnection()) {
            // Cập nhật bảng PET
            String updatePet = "UPDATE PET SET name = ?, age = ?, gender = ?, price = ?, vaccinated = ?, " +
                    "healthStatus = ?, origin = ?, weight = ?, furColor = ?, description = ?, supplierId = ?, role = ?, image = ? " +
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
                stmt.setString(12, role);
                stmt.setBytes(13,imageData);
                stmt.setInt(14, petId);
                int affected = stmt.executeUpdate();

                if (affected == 0) {
                    System.out.println("⚠️ Không tìm thấy thú cưng với petId: " + petId);
                    return;
                }
            }
            DeletePet.deletePetClassExtendsById(petId);

            // Cập nhật bảng phụ tương ứng theo role
            switch (role.toLowerCase()) {
                case "cat":
                    String insertCat = "INSERT INTO Cat (petId, isIndoor, breed, eyeColor) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement stmtCat = conn.prepareStatement(insertCat)) {
                        stmtCat.setInt(1, petId);
                        stmtCat.setBoolean(2, isIndoor);
                        stmtCat.setString(3, breed);
                        stmtCat.setString(4, eyeColor);
                        stmtCat.executeUpdate();
                    }
                    break;

                case "dog":
                    String insertDog = "INSERT INTO Dog (petId, breed, isTrained) VALUES (?, ?, ?)";
                    try (PreparedStatement stmtDog = conn.prepareStatement(insertDog)) {
                        stmtDog.setInt(1, petId);
                        stmtDog.setString(2, breed);
                        stmtDog.setBoolean(3, isTrained);
                        stmtDog.executeUpdate();
                    }
                    break;

                case "hamster":
                    String insertHamster = "INSERT INTO Hamster (petId, breed, tailLength) VALUES (?, ?, ?)";
                    try (PreparedStatement stmtHamster = conn.prepareStatement(insertHamster)) {
                        stmtHamster.setInt(1, petId);
                        stmtHamster.setString(2, breed);
                        stmtHamster.setDouble(3, tailLength);
                        stmtHamster.executeUpdate();
                    }
                    break;

                case "rabbit":
                    String insertRabbit = "INSERT INTO Rabbit (petId, breed, earLength) VALUES (?, ?, ?)";
                    try (PreparedStatement stmtRabbit = conn.prepareStatement(insertRabbit)) {
                        stmtRabbit.setInt(1, petId);
                        stmtRabbit.setString(2, breed);
                        stmtRabbit.setDouble(3, earLength);
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
