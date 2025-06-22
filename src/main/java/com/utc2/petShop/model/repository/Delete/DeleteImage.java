package com.utc2.petShop.model.repository.Delete;

import com.utc2.petShop.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteImage {
    public static boolean deleteImageById(int id) {
        String sqlDeleteImage = "DELETE FROM IMAGE WHERE imageId = ?";

        try(Connection conn = DBConnection.getConnection()){
            conn.setAutoCommit(false);

            try(PreparedStatement stmt = conn.prepareStatement(sqlDeleteImage)){
                stmt.setInt(1, id);
                int rowsAffected = stmt.executeUpdate();

                if(rowsAffected > 0){
                    conn.commit();
                    return true;
                }else {
                    conn.rollback();
                    return false;
                }
            }
            catch(SQLException e){
                conn.rollback();
                throw new RuntimeException("Lỗi khi xóa ảnh", e);
            }finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
