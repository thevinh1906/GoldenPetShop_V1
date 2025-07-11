package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;
import com.utc2.petShop.model.repository.Select.SelectBill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteCustomer {
//Xóa khách theo id
    public static boolean deleteCustomerById(int customerId) {
        String sqlSoftDeleteCustomer = "UPDATE CUSTOMER SET isDeleted = 1 WHERE customerId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            try {

                List<Integer> billIds = new ArrayList<>();
                billIds = SelectBill.getBillIDByCustomerId(customerId);
                for (Integer billId : billIds) {
                    DeleteBill.deleteBillById(billId);
                }


                // Xóa khách hàng
                try (PreparedStatement stmt = conn.prepareStatement(sqlSoftDeleteCustomer)) {
                    stmt.setInt(1, customerId);
                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        conn.commit();
                        return true;
                    } else {
                        conn.rollback();
                        return false;
                    }
                }
            } catch (Exception e) {
                conn.rollback();
                throw new RuntimeException("Lỗi khi xóa khách hàng và các hóa đơn", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Không thể kết nối hoặc lỗi SQL", e);
        }
    }
}



