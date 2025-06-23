package com.utc2.petShop.model.services;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.function.Consumer;

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

        if (!maximized) {
            primaryStage.sizeToScene(); // quan trọng: co Stage đúng theo nội dung
            primaryStage.centerOnScreen(); // căn giữa cửa sổ trên màn hình
        }

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

        if (!maximized) {
            primaryStage.sizeToScene(); // quan trọng: co Stage đúng theo nội dung
            primaryStage.centerOnScreen(); // căn giữa cửa sổ trên màn hình
        }

        stage.initModality(Modality.APPLICATION_MODAL); // Khóa cửa sổ trước nếu muốn
        stage.setResizable(maximized);
        stage.showAndWait(); // Chờ đóng mới về
    }

    public static <T> void openMoreScene(String fxml, String title, String css, boolean maximized, Consumer<T> controllerConsumer) {
        FXMLLoader loader = new FXMLLoader(scenes.class.getResource("/views/" + fxml + ".fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);

        T controller = loader.getController();
        controllerConsumer.accept(controller);

        scene.getStylesheets().add(String.valueOf(scenes.class.getResource("/styles/" + css + ".css")));
        Stage stage = new Stage();
        stage.getIcons().add(new Image("images/logo.png"));
        stage.setTitle(title);
        stage.setScene(scene);

        stage.setMaximized(maximized);

        if (!maximized) {
            primaryStage.sizeToScene(); // quan trọng: co Stage đúng theo nội dung
            primaryStage.centerOnScreen(); // căn giữa cửa sổ trên màn hình
        }
        stage.setResizable(maximized);
        stage.initModality(Modality.APPLICATION_MODAL); // Khóa cửa sổ trước nếu muốn
        stage.showAndWait(); // Chờ đóng mới về
    }

    public static void switchSceneNotTitleBar(String fxml, String title, String css, boolean maximized) throws IOException {
        FXMLLoader loader = new FXMLLoader(scenes.class.getResource("/views/" + fxml + ".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(String.valueOf(scenes.class.getResource("/styles/" + css + ".css")));
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.setMaximized(maximized);

        if (!maximized) {
            primaryStage.sizeToScene(); // quan trọng: co Stage đúng theo nội dung
            primaryStage.centerOnScreen(); // căn giữa cửa sổ trên màn hình
        }

        primaryStage.show();
    }
}
