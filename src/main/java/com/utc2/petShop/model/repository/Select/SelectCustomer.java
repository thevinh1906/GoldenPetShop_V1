package com.utc2.petShop.model.repository.Select;

import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectCustomer {
    public static List<Customer> getAllCustomers()  {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM CUSTOMER WHERE isDeleted = 0";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("customerId"));
                customer.setName(rs.getString("customerName"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
//                customer.setPoint(rs.getInt("point")); Xem lại có lấy nó không

                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customers;
    }

    public static Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM CUSTOMER WHERE customerId = ? AND isDeleted = 0";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setId(rs.getInt("customerId"));
                    customer.setName(rs.getString("customerName"));
                    customer.setPhoneNumber(rs.getString("phoneNumber"));
//                  customer.setPoint(rs.getInt("point")); // Nếu cần sử dụng thì mở dòng này

                    return customer;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }










    public static List<Customer> getCustomersByName(String keyword) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM CUSTOMER WHERE isDeleted = 0 AND LOWER(customerName) LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword.toLowerCase() + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setId(rs.getInt("customerId"));
                    customer.setName(rs.getString("customerName"));
                    customer.setPhoneNumber(rs.getString("phoneNumber"));
                    // Nếu cần dùng điểm thì mở comment dưới
                    // customer.setPoint(rs.getInt("point"));
                    customers.add(customer);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm khách hàng theo tên: " + keyword, e);
        }

        return customers;
    }

}



