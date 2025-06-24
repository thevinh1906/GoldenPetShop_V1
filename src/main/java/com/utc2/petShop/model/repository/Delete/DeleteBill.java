package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBill {
//Xóa Bill theo id
    public static boolean deleteBillById(int billId) {
        String sqlSoftDeleteBill = "UPDATE BILL SET isDeleted = 1 WHERE billId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlSoftDeleteBill)) {

            stmt.setInt(1, billId);
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Không thể xoá mềm hóa đơn (Bill) với ID = " + billId, e);
        }
    }
}

