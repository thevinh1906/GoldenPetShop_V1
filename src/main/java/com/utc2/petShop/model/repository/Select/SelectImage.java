package com.utc2.petShop.model.repository.Select;

import com.utc2.petShop.model.entities.Image.ImageByte;
import com.utc2.petShop.utils.DBConnection;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectImage {
    public static ImageByte getImageByImageId(int imageId) {
        String sql = "select * from IMAGE where imageId=?";
        ImageByte image = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, imageId);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    byte[] imageByte = rs.getBytes("imageByte");
                    image = new ImageByte(imageId, imageByte);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return image;
    }
}
