package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;
import com.utc2.petShop.model.repository.Select.SelectBill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteUser {
//Xóa khách hàng theo id
    public static boolean deleteUserById(int userId) {
        String deleteEmployeeSQL = "DELETE FROM EMPLOYEE WHERE userId = ?";
        String softDeleteUserSQL = "UPDATE USERS SET isDeleted = 1 WHERE userId = ?";
        String deleteFeedbackSQL = "DELETE FROM FEEDBACK WHERE userId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            try (
                    PreparedStatement stmtEmp = conn.prepareStatement(deleteEmployeeSQL);
                    PreparedStatement stmtFeedback = conn.prepareStatement(deleteFeedbackSQL);
                    PreparedStatement stmtUser = conn.prepareStatement(softDeleteUserSQL)
            ) {
                // Xóa nhân viên nếu có
                stmtEmp.setInt(1, userId);
                stmtEmp.executeUpdate();
                //xoá bill có nhân viên
                List<Integer> billIDs = new ArrayList<>();
                billIDs = SelectBill.getBillIDByUserId(userId);

                for (Integer billID : billIDs) {
                    DeleteBill.deleteBillById(billID);
                }

                // Xóa feedback nếu có
                stmtFeedback.setInt(1, userId);
                stmtFeedback.executeUpdate();

                // Xóa người dùng
                stmtUser.setInt(1, userId);
                int rowsAffected = stmtUser.executeUpdate();

                if (rowsAffected > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }

            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Lỗi khi xóa user, rollback...", e);
            } finally {
                conn.setAutoCommit(true); // Trả lại trạng thái mặc định
            }

        } catch (SQLException e) {
            throw new RuntimeException("Không thể kết nối hoặc lỗi SQL", e);
        }
    }

    public static boolean deleteUserClassExtendsById(int userId) {
        String deleteEmployeeSQL = "DELETE FROM EMPLOYEE WHERE userId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            try (
                    PreparedStatement stmtEmp = conn.prepareStatement(deleteEmployeeSQL);
            ) {
                // Xóa nhân viên nếu có
                stmtEmp.setInt(1, userId);
                int rowsAffected = stmtEmp.executeUpdate();


                if (rowsAffected > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }

            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Lỗi khi xóa user, rollback...", e);
            } finally {
                conn.setAutoCommit(true); // Trả lại trạng thái mặc định
            }

        } catch (SQLException e) {
            throw new RuntimeException("Không thể kết nối hoặc lỗi SQL", e);
        }
    }
}


