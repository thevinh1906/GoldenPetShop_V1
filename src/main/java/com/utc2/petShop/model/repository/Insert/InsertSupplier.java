package com.utc2.petShop.model.repository.Insert;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertSupplier {

    public static void insertSupplier (String name, String email, String phoneNumber, String address) {
        String insertSupplier = "INSERT INTO SUPPLIER (supplierName, email, phone, address) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSupplier)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phoneNumber);
            stmt.setString(4, address);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Thêm thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




