package com.utc2.petShop.model.repository.Select;

import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.repository.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectSupplier {

    public static Supplier getSupplierById(int supplierId)  {
        String sql = "SELECT * FROM SUPPLIER WHERE supplierId = ? AND isDeleted = 0";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<Supplier> getAllSuppliers()  {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM SUPPLIER WHERE isDelete = 0";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt("supplierId"));
                supplier.setName(rs.getString("supplierName"));
                supplier.setEmail(rs.getString("email"));
                supplier.setPhoneNumber(rs.getString("phone"));
                supplier.setAddress(rs.getString("address"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return suppliers;
    }
}
