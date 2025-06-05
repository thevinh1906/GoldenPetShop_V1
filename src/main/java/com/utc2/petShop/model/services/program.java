package com.utc2.petShop.model.services;
import com.utc2.petShop.utils.DBConnection;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

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
        scenes.switchScene("Admin/sampleHomeAdmin","Sign in","Admin/applicationHomeAdmin",true);
//        scenes.switchScene("sampleAddPet","Sign in","applicationAddPet",false);
//        scenes.switchScene("samplePet","Sign in","applicationPet",true);
//        scenes.switchScene("sampleHome","Sign in","applicationHome",true);
//        scenes.switchScene("samplePetTest","Sign in","applicationPetTest",true);

    }
}
