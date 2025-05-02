package com.utc2.petShop;

import com.utc2.petShop.model.entities.User.User;
import com.utc2.petShop.model.entities.User.Employee;
import com.utc2.petShop.model.entities.User.Admin;
import com.utc2.petShop.model.repository.SelectUser;
import com.utc2.petShop.model.repository.DBConnection;

import java.sql.Connection;
import java.util.List;

public class TestUser {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            SelectUser selectUser = new SelectUser(conn);
            List<User> list = selectUser.getAllUsers();

            for (User u : list) {
                System.out.println("Tên: " + u.getName());
                if (u instanceof Employee e) {
                    System.out.println("→ Nhân viên (" + e.getPosition() + ") - Lương: " + e.getSalary());
                } else if (u instanceof Admin) {
                    System.out.println("→ Quản trị viên");
                }
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
