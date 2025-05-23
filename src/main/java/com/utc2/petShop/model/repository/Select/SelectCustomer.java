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
        String sql = "SELECT * FROM CUSTOMER";

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
        String sql = "SELECT * FROM CUSTOMER WHERE customerId = ?";

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
}
