package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBill {

    public static boolean deleteBillById(int billId) {
        String sqlDeleteBillDetail = "DELETE FROM BILL_DETAIL WHERE billId = ?";
        String sqlDeleteBill = "DELETE FROM BILL WHERE billId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (
                    PreparedStatement stmtBillDetail = conn.prepareStatement(sqlDeleteBillDetail);
                    PreparedStatement stmtBill = conn.prepareStatement(sqlDeleteBill)
            ) {
                // Xóa chi tiết hóa đơn trước
                stmtBillDetail.setInt(1, billId);
                stmtBillDetail.executeUpdate();

                // Sau đó xóa hóa đơn chính
                stmtBill.setInt(1, billId);
                int affectedRows = stmtBill.executeUpdate();

                if (affectedRows > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Lỗi khi xóa hóa đơn. Đã rollback!", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Không thể kết nối CSDL hoặc lỗi SQL", e);
        }
    }
}

