package com.utc2.petShop;

import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.repository.SelectCustomer;

import java.sql.SQLException;
import java.util.List;

public class TestSelectCustomer {
    public static void main(String[] args) {
        try {
            List<Customer> customers = SelectCustomer.getAllCustomers();
            for (Customer customer : customers) {
                System.out.println("ID: " + customer.getId());
                System.out.println("Tên: " + customer.getName());
                System.out.println("Số điện thoại: " + customer.getPhoneNumber());
//                System.out.println("Điểm: " + customer.getPoint()); Xem lại
                System.out.println("-------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
