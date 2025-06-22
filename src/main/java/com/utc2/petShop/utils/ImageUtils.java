package com.utc2.petShop.utils;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.io.ByteArrayInputStream;

public class ImageUtils {
    public static Image cropToImageView(Image originalImage, double targetWidth, double targetHeight) {
        double imageWidth = originalImage.getWidth();
        double imageHeight = originalImage.getHeight();

        double imageRatio = imageWidth / imageHeight;
        double viewRatio = targetWidth / targetHeight;

        double scale;
        double cropWidth;
        double cropHeight;
        double x;
        double y;

        // So sánh tỷ lệ ảnh và ImageView để quyết định crop theo chiều nào
        if (imageRatio > viewRatio) {
            // Ảnh rộng hơn -> cắt chiều ngang
            cropHeight = imageHeight;
            cropWidth = cropHeight * viewRatio;
            x = (imageWidth - cropWidth) / 2;
            y = 0;
        } else {
            // Ảnh cao hơn -> cắt chiều dọc
            cropWidth = imageWidth;
            cropHeight = cropWidth / viewRatio;
            x = 0;
            y = (imageHeight - cropHeight) / 2;
        }

        PixelReader reader = originalImage.getPixelReader();
        WritableImage croppedImage = new WritableImage(reader, (int)x, (int)y, (int)cropWidth, (int)cropHeight);
        return croppedImage;
    }

    public static Image byteArrayToImage(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            // Trả về ảnh mặc định nếu không có dữ liệu
            return new Image(ImageUtils.class.getResource("/images/KhongTimDuocAnh.jpg").toExternalForm());
        }
        return new Image(new ByteArrayInputStream(bytes));
    }
}
