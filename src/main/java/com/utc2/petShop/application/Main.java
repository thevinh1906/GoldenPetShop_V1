package com.utc2.petShop.application;
import com.utc2.petShop.utils.DatabaseConnection;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.utc2.petShop.model.services.scenes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {

    public static void main(String[] args) {
//        try {
//            // Kết nối đến database
//            DatabaseConnection.connect();
//            Connection conn = DatabaseConnection.getConnection();
//
//            // Kiểm tra xem kết nối có thành công không
//            if (conn != null) {
//                System.out.println("Kết nối thành công!");

                launch(args);

//            }
//
//            // Đóng kết nối khi không cần dùng nữa
//            DatabaseConnection.closeConnection();
//
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.getIcons().add(new Image("images/logo.png"));
        scenes.setPrimaryStage(primaryStage);
//        scenes.switchScene("sampleSign_in","Sign in","applicationSign_in",false);
//        scenes.switchScene("sampleHomeAdmin","Sign in","applicationHomeAdmin",true);
        scenes.switchScene("sampleAddPet","Sign in","applicationAddPet",false);
    }
}
