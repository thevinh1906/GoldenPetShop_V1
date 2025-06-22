package com.utc2.petShop.model.repository.Select;

import com.utc2.petShop.model.entities.Image.ImageByte;
import com.utc2.petShop.model.entities.Product.*;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.utils.DBConnection;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectProduct {
    public static List<Product> getAllProducts()  {
        List<Product> products = new ArrayList<>();

        String sql = """
                    SELECT 
                        p.productId, p.name, p.price, p.quantity, p.description, p.manufacturer, p.supplierId,
                        a.type AS accessoryType, a.brand,
                        c.dimension, c.material AS cageMaterial,
                        f.expirationDate, f.flavor,
                        t.material AS toyMaterial, t.size
                    FROM PRODUCTS p
                    LEFT JOIN Accessory a ON p.productId = a.productId
                    LEFT JOIN Cage c ON p.productId = c.productId
                    LEFT JOIN Food f ON p.productId = f.productId
                    LEFT JOIN Toy t ON p.productId = t.productId
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("productId");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String manufacturer = rs.getString("manufacturer");
                int supplierId = rs.getInt("supplierId");

                // Lấy đối tượng Supplier từ DB
                Supplier supplier = SelectSupplier.getSupplierById(supplierId);
                List<ImageByte> images = getProductImageByProductId(id);

                Product p;

                if (rs.getString("accessoryType") != null) {
                    // Là Accessory
                    String type = rs.getString("accessoryType");
                    String brand = rs.getString("brand");
                    p = new Accessory(id, name, price, quantity, description, supplier, manufacturer, type, brand, images);
                } else if (rs.getString("dimension") != null) {
                    // Là Cage
                    String dimension = rs.getString("dimension");
                    String material = rs.getString("cageMaterial");
                    p = new Cage(id, name, price, quantity, description, supplier, manufacturer, dimension, material, images);
                } else if (rs.getDate("expirationDate") != null) {
                    // Là Food
                    Date expirationDate = rs.getDate("expirationDate");
                    String flavor = rs.getString("flavor");
                    p = new Food(id, name, price, quantity, description, supplier, manufacturer, expirationDate.toLocalDate(), flavor, images);
                } else if (rs.getString("toyMaterial") != null) {
                    // Là Toy
                    String material = rs.getString("toyMaterial");
                    String size = rs.getString("size");
                    p = new Toy(id, name, price, quantity, description, supplier, manufacturer, material, size, images);
                } else {
                    // Là Product thường
                    p = new Product(id, name, price, quantity, description, supplier, manufacturer, images);
                }

                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }


    public static List<Integer> getProductIDBySupplierId(int supplierId) {
        List<Integer> productIDs = new ArrayList<>();
        String sql = "SELECT productId FROM PRODUCTS WHERE supplierId = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supplierId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("productId");

                    productIDs.add(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productIDs;
    }

    public static List<ImageByte> getProductImageByProductId(int productId) {
        List<ImageByte> productImages = new ArrayList<>();
        String sql = """
                SELECT I.image, I.imageId
                FROM IMAGE I
                JOIN IMAGE_PRODUCT IP ON I.imageId = IP.imageId
                WHERE IP.productId = ?
                """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    byte[] images = rs.getBytes("image");
                    int imageId = rs.getInt("imageId");
                    ImageByte image;
                    if (images != null) {
                        image = new ImageByte(imageId, images);
                        productImages.add(image);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productImages;
    }
}
