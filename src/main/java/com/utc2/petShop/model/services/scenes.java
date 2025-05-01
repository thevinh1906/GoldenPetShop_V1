package com.utc2.petShop.model.services;

import com.utc2.petShop.controllers.controllerEditPet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
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
    public static void openMoreScene(String fxml, String title, String css, boolean maximized) throws IOException {
        FXMLLoader loader = new FXMLLoader(scenes.class.getResource("/views/" + fxml + ".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(String.valueOf(scenes.class.getResource("/styles/" + css + ".css")));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("images/logo.png"));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setMaximized(maximized);
        stage.initModality(Modality.APPLICATION_MODAL); // Khóa cửa sổ trước nếu muốn
        stage.setResizable(false);
        stage.showAndWait(); // Chờ đóng mới về
    }
    public static void openMoreSceneEdit(String fxml, String title, String css, boolean maximized, Object object) throws IOException {
        FXMLLoader loader = new FXMLLoader(scenes.class.getResource("/views/" + fxml + ".fxml"));
        Parent root = loader.load();
        ModuleLayer.Controller controller = loader.getController();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(String.valueOf(scenes.class.getResource("/styles/" + css + ".css")));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("images/logo.png"));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setMaximized(maximized);
        stage.initModality(Modality.APPLICATION_MODAL); // Khóa cửa sổ trước nếu muốn
        stage.setResizable(false);
        stage.showAndWait(); // Chờ đóng mới về
    }
}
