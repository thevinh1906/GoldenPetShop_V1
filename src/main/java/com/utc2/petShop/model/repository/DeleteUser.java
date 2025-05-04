package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteUser {
    private static Connection conn;

    public DeleteUser(Connection conn) {
        DeleteUser.conn = conn;
    }

    static {
        try {
            conn = DBConnection.getConnection();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteUserById(int userId) throws SQLException {
        String deleteEmployeeSQL = "DELETE FROM EMPLOYEE WHERE userId = ?";
        String deleteUserSQL = "DELETE FROM USERS WHERE userId = ?";
        String deleteFEEDBACKSQL = "DELETE FROM FEEDBACK WHERE userId = ?";

        try (
                PreparedStatement stmtEmp = conn.prepareStatement(deleteEmployeeSQL);
                PreparedStatement stmtUser = conn.prepareStatement(deleteUserSQL);
                PreparedStatement stmtFEEDBACK = conn.prepareStatement(deleteFEEDBACKSQL)
        ) {
            // Xóa nhân viên nếu tồn tại
            stmtEmp.setInt(1, userId);
            stmtEmp.executeUpdate();

            //xoá bill có nhân viên
            List<Integer> billIDs = new ArrayList<>();
            billIDs = SelectBill.getBillIDByUserId(userId);

            for (Integer billID : billIDs) {
                DeleteBill.deleteBillById(billID);
            }

            //xoá FEEDBACK nếu tồn tại

            stmtFEEDBACK.setInt(1, userId);
            stmtFEEDBACK.executeUpdate();

            // Xóa user
            stmtUser.setInt(1, userId);
            int affectedRows = stmtUser.executeUpdate();

            return affectedRows > 0;
        }
    }
}
