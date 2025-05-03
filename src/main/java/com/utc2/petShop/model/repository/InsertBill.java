package com.utc2.petShop.model.repository;

import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.entities.User.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Date;

public class InsertBill {

    public static void insertBill (int id, Employee employee, Customer customer, Date invoiceDate, double totalAmount, String paymentMethod, String status) {
        String insertBill = "INSERT INTO SUPPLIER (billId, userId, customerId, date, totalAmount, paymentMethod, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertBill)) {

            stmt.setInt(1, id);
            stmt.setInt(2, employee.getId());
            stmt.setInt(3, customer.getId());
            stmt.setDate(4, (java.sql.Date) invoiceDate);
            stmt.setDouble(5, totalAmount);
            stmt.setString(6, paymentMethod);
            stmt.setString(7, status);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Thêm thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

