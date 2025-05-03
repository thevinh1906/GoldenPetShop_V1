package com.utc2.petShop.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Date;

public class InsertSupplier {
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=PetShopManagement;encrypt=true;trustServerCertificate=true";
    private static final String user = "sa";
    private static final String password = "123456";

    public static void insertSupplier (int id, String name, String email, String phoneNumber, String address) {
        String insertSupplier = "INSERT INTO SUPPLIER (supplierId, supplierName, email, phone, address) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(insertSupplier)) {

            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, address);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Thêm thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

