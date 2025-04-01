package com.utc2.petShop.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class scenes {
    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void switchScene(String fxml, String title, String css, boolean maximized) throws IOException {
        FXMLLoader loader = new FXMLLoader(scenes.class.getResource("/views/" + fxml + ".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(String.valueOf(scenes.class.getResource("/styles/" + css + ".css")));
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.setMaximized(maximized);
        primaryStage.show();
    }
}
