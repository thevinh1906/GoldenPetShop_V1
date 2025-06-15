package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Pet.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerPet implements Initializable {

    @FXML
    private GridPane gridPaneCat;

    @FXML
    private GridPane gridPaneDog;

    @FXML
    private GridPane gridPaneGeneral;

    @FXML
    private GridPane gridPaneHamster;

    @FXML
    private GridPane gridPaneRabbit;

    @FXML
    private ImageView imageViewPet;

    @FXML
    private Label labelAgeGeneral;

    @FXML
    private Label labelBreedGeneral;

    @FXML Label labelDecribe;

    @FXML
    private Label labelEarLenghtRabbit;

    @FXML
    private Label labelEyeColorCat;

    @FXML
    private Label labelFurColorGeneral;

    @FXML
    private Label labelGenderGeneral;

    @FXML
    private Label labelHealthStatusGeneral;

    @FXML
    private Label labelIDGeneral;

    @FXML
    private Label labelIndoorCat;

    @FXML
    private Label labelNameGeneral;

    @FXML
    private Label labelOriginGeneral;

    @FXML
    private Label labelPriceGeneral;

    @FXML
    private Label labelSupplierGeneral;

    @FXML
    private Label labelTailLenghtHamster;

    @FXML
    private Label labelTrainDog;

    @FXML
    private Label labelVaccinatedGeneral;

    @FXML
    private Label labelWeightGeneral;

    @FXML
    private Line line;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vBox;

    @FXML
    private HBox hBox;

    @FXML
    private StackPane stackPaneImage;

    public void hideScreen() {
        gridPaneDog.setVisible(false);
        gridPaneHamster.setVisible(false);
        gridPaneRabbit.setVisible(false);
        gridPaneCat.setVisible(false);
        gridPaneDog.setManaged(false);
        gridPaneHamster.setManaged(false);
        gridPaneRabbit.setManaged(false);
        gridPaneCat.setManaged(false);
    }

    public void receiveData(Pet obj) {
        Image image = cropToImageView(obj.getImage(),imageViewPet.getFitWidth(),imageViewPet.getFitHeight());
        imageViewPet.setImage(image);
        labelIDGeneral.setText(String.valueOf("ID: PE" + obj.getId()));
        labelNameGeneral.setText("Name: " + obj.getName());
        labelAgeGeneral.setText(String.valueOf("Age: " + obj.getAge() + " month"));
        String genderText;
        if (obj.isGender()) {
            genderText = "Gender: Male";
        } else {
            genderText = "Gender: Female";
        }
        labelGenderGeneral.setText(genderText);
        labelFurColorGeneral.setText("Fur color: " + obj.getFurColor());
        labelWeightGeneral.setText(String.valueOf("Weight: " + obj.getWeight() + " Kg"));
        labelPriceGeneral.setText(String.valueOf("Price: " + obj.getPrice() + "$"));
        if (obj.isVaccinated()) {
            labelVaccinatedGeneral.setText("Vaccinated: Injected");
        } else {
            labelVaccinatedGeneral.setText("Vaccinated: Unvaccinated");
        }
        labelHealthStatusGeneral.setText("Health status: " + obj.getHealthStatus());
        labelSupplierGeneral.setText(String.valueOf("Supplier: " + obj.getSupplier()));
        labelDecribe.setText(obj.getDescription());
        labelOriginGeneral.setText(String.valueOf("Origin: " + obj.getOrigin()));
        if (obj instanceof Dog) {
            Dog dog = (Dog) obj;
            labelBreedGeneral.setText(String.valueOf("Breed: " + dog.getBreed().getBreed()));
            if (dog.isIsTrained()) {
                labelTrainDog.setText("Trained");
            } else {
                labelTrainDog.setText("Untrained");
            }

            hideScreen();

            gridPaneDog.setVisible(true);
            gridPaneDog.setManaged(true);
        } else if (obj instanceof Cat) {
            Cat cat = (Cat) obj;
            labelBreedGeneral.setText(String.valueOf("Breed: " + cat.getBreed().getBreed()));
            if (cat.isIsIndoor()) {
                labelIndoorCat.setText("Indoor");
            } else {
                labelIndoorCat.setText("Unindoor");
            }
            labelEyeColorCat.setText(String.valueOf("Eye color: " + cat.getEyeColor()));

            hideScreen();

            gridPaneCat.setVisible(true);
            gridPaneCat.setManaged(true);
        } else if (obj instanceof Hamster) {
            Hamster hamster = (Hamster) obj;
            labelBreedGeneral.setText(String.valueOf("Breed: " + hamster.getBreed().getBreed()));
            labelTailLenghtHamster.setText(String.valueOf("Tail Length: " + hamster.getTailLength()));

            hideScreen();
            gridPaneHamster.setVisible(true);
            gridPaneHamster.setManaged(true);
        } else if (obj instanceof Rabbit) {
            Rabbit rabbit = (Rabbit) obj;
            labelBreedGeneral.setText(String.valueOf("Breed: " + rabbit.getBreed().getBreed()));
            labelEarLenghtRabbit.setText(String.valueOf("Ear Length: " + rabbit.getEarLength()));

            hideScreen();
            gridPaneRabbit.setVisible(true);
            gridPaneRabbit.setManaged(true);
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        imageViewPet.setClip(new Circle(150, 150, 150));

        Platform.runLater(() -> {
            double w = imageViewPet.getFitWidth();
            double h = imageViewPet.getFitHeight();
            double r = Math.min(w, h) / 2;

            Circle clip = new Circle(w / 2, h / 2, r);
            imageViewPet.setClip(clip);
        });


        hideScreen();

        line.endYProperty().bind(vBox.heightProperty());
    }



    public Image cropToImageView(Image originalImage, double targetWidth, double targetHeight) {
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
}


