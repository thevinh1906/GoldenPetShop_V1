package com.utc2.petShop.model.repository.Select;

import com.utc2.petShop.model.entities.RevenueReport.RevenueReport;
import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectRevenueReport {
    public static List<RevenueReport> getAllRevenueReports() {
        List<RevenueReport> reports = new ArrayList<>();
        String sql = "SELECT * FROM REVENUE_REPORT";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("reportId");
                int month = rs.getInt("month");
                int year = rs.getInt("year");
                double totalRevenue = rs.getDouble("totalRevenue");
                int totalBill = rs.getInt("totalBill");

                reports.add(new RevenueReport(id, month, year, totalRevenue, totalBill));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reports;
    }
}



