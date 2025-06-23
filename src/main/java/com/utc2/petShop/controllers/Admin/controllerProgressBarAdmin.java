package com.utc2.petShop.controllers.Admin;

import com.utc2.petShop.model.services.scenes;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controllerProgressBarAdmin implements Initializable {

    @FXML
    private ProgressBar probar;

    @FXML
    private Pane panebar;

    @FXML
    private ImageView imageView;

    private static Stage primaryStage = scenes.getPrimaryStage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        imageView.layoutXProperty().bind(
                probar.widthProperty()
                        .multiply(probar.progressProperty()).subtract(50)
        );



        probar.progressProperty().bind(loadTask.progressProperty());

        loadTask.setOnSucceeded(evt -> {
            primaryStage.setScene(loadTask.getValue());
            primaryStage.setTitle("Golden Pet Shop");
            primaryStage.setMaximized(true);

            primaryStage.show();
        });




        loadTask.setOnFailed(evt -> {
            loadTask.getException().printStackTrace();
        });

        Thread th = new Thread(loadTask);
        th.setDaemon(true);
        th.start();
    }

    Task<Scene> loadTask = new Task<>(){

        @Override
        protected Scene call() throws Exception {
            smoothProgress(0, 0.2);
            FXMLLoader loader = new FXMLLoader(scenes.class.getResource("/views/Admin/sampleHomeAdmin.fxml"));


            Parent root = loader.load();
            smoothProgress(0.2, 0.6);


            Scene scene = new Scene(root);
            scene.getStylesheets().add(String.valueOf(scenes.class.getResource("/styles/Admin/applicationHomeAdmin.css")));
            smoothProgress(0.6, 0.8);

            smoothProgress(0.8, 1.0);


            return scene;
        }


        private void smoothProgress(double from, double to) throws InterruptedException {
            int steps = (int)((to - from) * 100);       // mỗi bước 0.01
            for (int i = 0; i <= steps; i++) {
                double progress = from + i * (to - from) / steps;
                updateProgress(progress, 1);
                Thread.sleep(10);
            }
        }

    };


}
