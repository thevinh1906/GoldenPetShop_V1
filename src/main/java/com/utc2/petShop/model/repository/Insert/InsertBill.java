package com.utc2.petShop.model.repository.Insert;

import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.entities.User.Employee;
import com.utc2.petShop.model.repository.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class InsertBill {

    public static void insertBill (Employee employee, Customer customer, Date invoiceDate, double totalAmount, String paymentMethod, String status) {
        String insertBill = "INSERT INTO BILL (userId, customerId, date, totalAmount, paymentMethod, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertBill)) {

            stmt.setInt(1, employee.getId());
            stmt.setInt(2, customer.getId());
            stmt.setDate(3, (java.sql.Date) invoiceDate);
            stmt.setDouble(4, totalAmount);
            stmt.setString(5, paymentMethod);
            stmt.setString(6, status);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Thêm thành công.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

