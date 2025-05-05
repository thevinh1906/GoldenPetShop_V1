package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.entities.User.Employee;
import com.utc2.petShop.model.repository.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateBill {

    public static void updateBill(int billId, Employee employee, Customer customer, LocalDate invoiceDate,
                                  double totalAmount, String paymentMethod, String status) {
        String updateQuery = "UPDATE BILL SET userId = ?, customerId = ?, date = ?, totalAmount = ?, " +
                "paymentMethod = ?, status = ? WHERE billId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setInt(1, employee.getId());
            stmt.setInt(2, customer.getId());
            stmt.setDate(3, Date.valueOf(invoiceDate));
            stmt.setDouble(4, totalAmount);
            stmt.setString(5, paymentMethod);
            stmt.setString(6, status);
            stmt.setInt(7, billId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập nhật hóa đơn thành công.");
            } else {
                System.out.println("⚠️ Không tìm thấy hóa đơn với billId: " + billId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật hóa đơn: " + e.getMessage());
        }
    }
}
