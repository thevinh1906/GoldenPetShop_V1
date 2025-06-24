package com.utc2.petShop.model.repository.Select;

import com.utc2.petShop.model.entities.Bill.Bill;
import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.entities.Image.ImageByte;
import com.utc2.petShop.model.entities.User.Employee;
import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SelectBill {
    public static List<Bill> getAllBills()  {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM BILL WHERE isDeleted = 0";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("billId");
                int customerId = rs.getInt("customerId");
                int employeeId = rs.getInt("userId");
                LocalDate invoiceDate = rs.getDate("date").toLocalDate();
                double totalAmount = rs.getDouble("totalAmount");
                String paymentMethod = rs.getString("paymentMethod");
                String status = rs.getString("status");

                // Lấy Customer
                Customer customer = SelectCustomer.getCustomerById(customerId);

                // Lấy Employee (dựa vào SelectUser)
                Employee employee = getEmployeeById(employeeId);

                Bill bill = new Bill(id, customer, employee, invoiceDate, totalAmount, paymentMethod, status);
                bills.add(bill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bills;
    }

    // Tìm trong SelectUser để lấy đúng kiểu là Employee
    private static Employee getEmployeeById(int employeeId)  {
        String sql = """
            SELECT u.*, e.position, e.salary, e.workingHours
            FROM USERS u
            LEFT JOIN EMPLOYEE e ON u.userId = e.userId
            WHERE u.userId = ? AND isDeleted = 0
        """;
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String positionStr = rs.getString("position");
                    if (positionStr != null) {
                        ImageByte imageByte = SelectImage.getImageByImageId(rs.getInt("imageId"));
                        return new Employee(
                                rs.getInt("userId"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("fullName"),
                                rs.getBoolean("gender"),
                                rs.getString("email"),
                                rs.getString("phoneNumber"),
                                rs.getString("address"),
                                rs.getDate("birthDate").toLocalDate(),
                                rs.getDate("createAt").toLocalDate(),
                                imageByte,
                                parsePosition(positionStr),
                                rs.getDouble("salary"),
                                rs.getString("workingHours")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Chuyển string sang enum EEmployeePosition
    private static com.utc2.petShop.model.entities.User.EEmployeePosition parsePosition(String posStr) {
        for (com.utc2.petShop.model.entities.User.EEmployeePosition p : com.utc2.petShop.model.entities.User.EEmployeePosition.values()) {
            if (p.getPosition().equals(posStr)) {
                return p;
            }
        }
        return null;
    }

    public static List<Integer> getBillIDByUserId(int userId)  {
        List<Integer> billIDs = new ArrayList<>();
        String sql = "SELECT billId FROM BILL WHERE userId = ? AND isDeleted = 0";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("billId");

                    billIDs.add(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return billIDs;
    }

    public static List<Integer> getBillIDByCustomerId(int customerId) {
        List<Integer> billIDs = new ArrayList<>();
        String sql = "SELECT billId FROM BILL WHERE customerId = ? AND isDeleted = 0";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("billId");

                    billIDs.add(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return billIDs;
    }



    public static List<Bill> getBillsByName(String keyword) {
        List<Bill> bills = new ArrayList<>();
        String sql = """
        SELECT b.*
        FROM BILL b
        JOIN CUSTOMER c ON b.customerId = c.customerId
        JOIN USERS u ON b.userId = u.userId
        WHERE b.isDeleted = 0 AND (
            LOWER(c.customerName) LIKE ? OR LOWER(u.fullName) LIKE ?
        )
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String loweredKeyword = "%" + keyword.toLowerCase() + "%";
            stmt.setString(1, loweredKeyword);
            stmt.setString(2, loweredKeyword);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("billId");
                    int customerId = rs.getInt("customerId");
                    int employeeId = rs.getInt("userId");
                    LocalDate invoiceDate = rs.getDate("date").toLocalDate();
                    double totalAmount = rs.getDouble("totalAmount");
                    String paymentMethod = rs.getString("paymentMethod");
                    String status = rs.getString("status");

                    Customer customer = SelectCustomer.getCustomerById(customerId);
                    Employee employee = getEmployeeById(employeeId);

                    Bill bill = new Bill(id, customer, employee, invoiceDate, totalAmount, paymentMethod, status);
                    bills.add(bill);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm hóa đơn theo tên khách hàng hoặc nhân viên: " + keyword, e);
        }

        return bills;
    }



}
