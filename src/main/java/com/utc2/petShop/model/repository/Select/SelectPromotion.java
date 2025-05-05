package com.utc2.petShop.model.repository.Select;

import com.utc2.petShop.model.entities.Promotion.Promotion;
import com.utc2.petShop.model.repository.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectPromotion {
    public static List<Promotion> getAllPromotions() {
        List<Promotion> promotions = new ArrayList<>();
        String sql = "SELECT * FROM PROMOTION";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Promotion promotion = new Promotion();
                promotion.setId(rs.getInt("promotionId"));
                promotion.setName(rs.getString("name"));
                promotion.setDiscountPercent(rs.getFloat("discountPercentage"));
                promotion.setStartDate(rs.getDate("startDate").toLocalDate());
                promotion.setEndDate(rs.getDate("endDate").toLocalDate());
                promotion.setIsActive(rs.getBoolean("isActive"));

                promotions.add(promotion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return promotions;
    }
}
