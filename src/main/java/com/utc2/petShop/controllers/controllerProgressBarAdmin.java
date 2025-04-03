package com.utc2.petShop.controllers;

import com.utc2.petShop.model.services.scenes;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controllerProgressBarAdmin implements Initializable {

    @FXML
    private ProgressBar probar;

    @FXML
    private Pane panebar;

    private ImageView image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image = new ImageView(new Image(getClass().getResource("/images/shiba.png").toExternalForm()));
        image.setFitHeight(90);
        image.setFitWidth(90);
        panebar.getChildren().add(image);
        doword task = new doword();
        probar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }
    public class doword extends Task<Void> {
        @Override
        protected Void call() throws Exception {
            for (int i = 0; i < 1000; i++) {
                updateProgress(i+1, 1000);
                javafx.application.Platform.runLater(() ->
                        image.setLayoutX(probar.getWidth() * probar.getProgress())
                );

                Thread.sleep(2);
            }
            javafx.application.Platform.runLater(() -> {
                try {
                    scenes.switchScene("sampleHomeAdmin", "Golden Pet Shop","applicationHomeAdmin", true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return null;
        }
    }
}
