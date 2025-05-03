package com.utc2.petShop.model.repository;

import com.utc2.petShop.model.entities.Customer.Customer;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectCustomer {
    private static Connection conn;

    public SelectCustomer(Connection conn) {
        this.conn = conn;
    }

    static {
        try {
            conn = DBConnection.getConnection();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM CUSTOMER";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("customerId"));
                customer.setName(rs.getString("customerName"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
//                customer.setPoint(rs.getInt("point")); Xem lại có lấy nó không

                customers.add(customer);
            }
        }

        return customers;
    }

    public static Customer getCustomerById(int customerId) throws SQLException {
        String sql = "SELECT * FROM CUSTOMER WHERE customerId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        }

        return null;
    }
}
