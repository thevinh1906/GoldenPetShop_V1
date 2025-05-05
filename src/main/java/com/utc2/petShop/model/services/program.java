package com.utc2.petShop.model.services;
import com.utc2.petShop.model.repository.DBConnection;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.utc2.petShop.model.services.scenes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class program extends Application {

    public static void main(String[] args) {
        // Kết nối đến database
        Connection conn = DBConnection.getConnection();

        // Kiểm tra xem kết nối có thành công không
        if (conn != null) {
            System.out.println("Kết nối thành công!");

            launch(args);

        }

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.getIcons().add(new Image("images/logo.png"));
        scenes.setPrimaryStage(primaryStage);
//        scenes.switchScene("sampleSign_in","Sign in","applicationSign_in",false);
        scenes.switchScene("sampleHomeAdmin","Sign in","applicationHomeAdmin",true);
//        scenes.switchScene("sampleAddPet","Sign in","applicationAddPet",false);
//        scenes.switchScene("samplePet","Sign in","applicationPet",true);
    }
}
