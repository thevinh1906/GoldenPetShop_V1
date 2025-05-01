package com.utc2.petShop;

import com.utc2.petShop.model.repository.DBConnection;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Kết nối thành công tới cơ sở dữ liệu!");
            } else {
                System.out.println("Kết nối thất bại (Connection is null).");
            }
        } catch (Exception e) {
            System.err.println("Kết nối thất bại:");
            e.printStackTrace();
        }
    }
}
