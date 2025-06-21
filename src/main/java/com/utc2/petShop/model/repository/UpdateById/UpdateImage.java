package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.entities.Image.ImageByte;
import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateImage {
    public static void updateImage(ImageByte image) {
        String updateQuery = "UPDATE IMAGE SET image = ?" +
                "WHERE imageId = ?";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setBytes(1,image.getImage());
            stmt.setInt(2,image.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập ảnh hàng thành công.");
            } else {
                System.out.println("⚠️ Không tìm thấy ảnh với imageId: " + image.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật ảnh: " + e.getMessage());
        }
    }
}
