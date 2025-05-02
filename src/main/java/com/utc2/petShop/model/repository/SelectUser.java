package com.utc2.petShop.model.repository;

import com.utc2.petShop.model.entities.User.User;
import com.utc2.petShop.model.entities.User.Employee;
import com.utc2.petShop.model.entities.User.Admin;
import com.utc2.petShop.model.entities.User.EEmployeePosition;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SelectUser {
    private static Connection conn;

    public SelectUser(Connection conn) {
        this.conn = conn;
    }

    static{
        try{
            conn = DBConnection.getConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        String sql = """
        SELECT u.*, e.position, e.salary, e.workingHours
        FROM USERS u
        LEFT JOIN EMPLOYEE e ON u.userId = e.userId
        """;

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("userId");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                boolean gender = rs.getBoolean("gender");
                String email = rs.getString("email");
                String phone = rs.getString("phoneNumber");
                String address = rs.getString("address");
                LocalDate birth = rs.getDate("birthDate").toLocalDate();
                LocalDate createAt = rs.getDate("createAt").toLocalDate();

                String positionStr = rs.getString("position");

                if (positionStr != null) {
                    // Là Employee
                    double salary = rs.getDouble("salary");
                    String workingHours = rs.getString("workingHours");

                    EEmployeePosition pos = null;
                    for (EEmployeePosition p : EEmployeePosition.values()) {
                        if (p.getPosition().equals(positionStr)) {
                            pos = p;
                            break;
                        }
                    }

                    Employee emp = new Employee(id, username, password, fullName, gender, email, phone, address,
                            birth, createAt, pos, salary, workingHours);
                    users.add(emp);
                } else {
                    // Không có position → là Admin
                    Admin admin = new Admin(id, username, password, fullName, gender, email, phone, address, birth, createAt);
                    users.add(admin);
                }
            }
        }

        return users;
    }
}
