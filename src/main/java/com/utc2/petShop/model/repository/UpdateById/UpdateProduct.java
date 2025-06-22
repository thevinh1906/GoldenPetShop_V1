package com.utc2.petShop.model.repository.UpdateById;

import com.utc2.petShop.model.entities.Image.ImageByte;
import com.utc2.petShop.model.entities.Product.Product;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.repository.Delete.DeleteImage;
import com.utc2.petShop.model.repository.Insert.InsertImage;
import com.utc2.petShop.model.repository.Insert.InsertProduct;
import com.utc2.petShop.utils.DBConnection;
import com.utc2.petShop.model.repository.Delete.DeleteProduct;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class UpdateProduct {

    public static void updateProduct(int productId, Supplier supplier, String name, double price, int quantity,
                                     String description, String manufacturer, String type, String brand,
                                     LocalDate expirationDate, String flavor, String dimension,
                                     String material, String size, String role, List<ImageByte> images) {

        String updateProduct = "UPDATE PRODUCTS SET supplierId = ?, name = ?, price = ?, quantity = ?, " +
                "description = ?, manufacturer = ?, role = ? WHERE productId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateProduct)) {

            // Cập nhật bảng PRODUCTS
            stmt.setInt(1, supplier.getId());
            stmt.setString(2, name);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);
            stmt.setString(5, description);
            stmt.setString(6, manufacturer);
            stmt.setString(7, role);
            stmt.setInt(8, productId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập nhật sản phẩm thành công.");
                updateImageProduct(productId, images);
            } else {
                System.out.println("⚠️ Không tìm thấy sản phẩm với productId: " + productId);
                return;
            }

            DeleteProduct.deleteProductClassExtendsById(productId);

            // Cập nhật bảng phụ tùy theo role
            switch (role.toLowerCase()) {
                case "accessory":
                    String insertAccessory = "INSERT INTO Accessory (productId, type, brand) VALUES (?, ?, ?)";
                    try (PreparedStatement stmtAccessory = conn.prepareStatement(insertAccessory)) {
                        stmtAccessory.setInt(1, productId);
                        stmtAccessory.setString(2, type);
                        stmtAccessory.setString(3, brand);
                        stmtAccessory.executeUpdate();
                    }
                    break;

                case "cage":
                    String insertCage = "INSERT INTO Cage (productId, dimension, material) VALUES (?, ?, ?)";
                    try (PreparedStatement stmtCage = conn.prepareStatement(insertCage)) {
                        stmtCage.setInt(1, productId);
                        stmtCage.setString(2, dimension);
                        stmtCage.setString(3, material);
                        stmtCage.executeUpdate();
                    }
                    break;

                case "food":
                    String insertFood = "INSERT INTO Food (productId, expirationDate, flavor) VALUES (?, ?, ?)";
                    try (PreparedStatement stmtFood = conn.prepareStatement(insertFood)) {
                        stmtFood.setInt(1, productId);
                        stmtFood.setDate(2, Date.valueOf(expirationDate));
                        stmtFood.setString(3, flavor);
                        stmtFood.executeUpdate();
                    }
                    break;

                case "toy":
                    String insertToy = "INSERT INTO Toy (productId, material, size) VALUES (?, ?, ?)";
                    try (PreparedStatement stmtToy = conn.prepareStatement(insertToy)) {
                        stmtToy.setInt(1, productId);
                        stmtToy.setString(2, material);
                        stmtToy.setString(3, size);
                        stmtToy.executeUpdate();
                    }
                    break;

                default:
                    System.out.println("⚠️ Không xác định được role phù hợp để cập nhật bảng phụ.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }

    public static void updateQuantityProduct(int productId, int quantity) {

        String updateQuantityProduct = "UPDATE PRODUCTS SET quantity = quantity + ? WHERE productId = ?";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuantityProduct)) {

            // Cập nhật bảng PRODUCTS
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập nhật sản phẩm thành công.");
            } else {
                System.out.println("⚠️ Không tìm thấy sản phẩm với productId: " + productId);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }

    public static void updateImageProduct(int productId, List<ImageByte> images) {
        for(ImageByte image : images) {
            if (image.getImage() != null && image.getId() != 0) {
                UpdateImage.updateImage(image);
            }
            else if(image.getImage() == null && image.getId() != 0) {
                DeleteProduct.deleteImageProductByProductIdAndImageId(productId, image.getId());
            }
            else if(image.getImage() != null && image.getId() == 0) {
                InsertProduct.insertImage(productId,image.getImage());
            }
        }
    }
}
