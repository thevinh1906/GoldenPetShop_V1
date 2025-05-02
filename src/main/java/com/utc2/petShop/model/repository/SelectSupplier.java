package com.utc2.petShop.model.repository;

import com.utc2.petShop.model.entities.Supplier.Supplier;

import java.io.IOException;
import java.sql.*;

public class SelectSupplier {
    private static Connection conn;

    public SelectSupplier(Connection conn) {
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
    public static Supplier getSupplierById(int supplierId) throws SQLException {
        String sql = "SELECT * FROM SUPPLIER WHERE supplierId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supplierId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Supplier supplier = new Supplier();
                    supplier.setId(rs.getInt("supplierId"));
                    supplier.setName(rs.getString("supplierName"));
                    supplier.setEmail(rs.getString("email"));
                    supplier.setPhoneNumber(rs.getString("phone"));
                    supplier.setAddress(rs.getString("address"));
                    return supplier;
                }
            }
        }
        return null;
    }
}
