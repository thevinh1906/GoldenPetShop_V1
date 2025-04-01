package petShop.frontend;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import petShop.services.scenes;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.getIcons().add(new Image("images/logo.png"));
        scenes.setPrimaryStage(primaryStage);
        scenes.switchScene("sampleSign_in","Sign in","applicationSign_in",false);
    }
}
