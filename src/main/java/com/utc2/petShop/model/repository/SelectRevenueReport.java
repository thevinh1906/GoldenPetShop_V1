package com.utc2.petShop.model.repository;

import com.utc2.petShop.model.entities.RevenueReport.RevenueReport;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectRevenueReport {
    private static Connection conn;

    public SelectRevenueReport(Connection conn) {
        this.conn = conn;
    }
    static {
        try {
            conn = DBConnection.getConnection();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<RevenueReport> getAllRevenueReports() throws SQLException {
        List<RevenueReport> reports = new ArrayList<>();
        String sql = "SELECT * FROM REVENUE_REPORT";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int month = rs.getInt("month");
                int year = rs.getInt("year");
                double totalRevenue = rs.getDouble("totalRevenue");
                int totalBill = rs.getInt("totalBill");

                reports.add(new RevenueReport(id, month, year, totalRevenue, totalBill));
            }
        }

        return reports;
    }
}
