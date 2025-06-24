package com.utc2.petShop.model.repository.Insert;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class InsertPromotion {
    public static void insertPromotion(String name, float discountPercentage, LocalDate startDate, LocalDate endDate, Boolean isActive) {
        String insertPromotion = "INSERT INTO PROMOTION (name, discountPercentage, startDate, endDate, isActive) \n" +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertPromotion)) {

            stmt.setString(1, name);
            stmt.setFloat(2, discountPercentage);
            stmt.setDate(3, Date.valueOf(startDate));
            stmt.setDate(4, Date.valueOf(endDate));
            stmt.setBoolean(5, isActive);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Thêm thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



