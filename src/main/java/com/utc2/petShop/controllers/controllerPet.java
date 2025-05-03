package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.entities.Pet.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerPet implements Initializable {

    @FXML
    private ScrollPane ScrollCenter;

    @FXML
    private VBox VBoxSrcollCenter;

    @FXML
    private Button buttonFind;

    @FXML
    private Button buttonSetting;

    @FXML
    private Button buttonUser;

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
    private ImageView inageViewPet;

    @FXML
    private Label labelAgeGeneral;

    @FXML
    private Label labelBreedCat;

    @FXML
    private Label labelBreedDog;

    @FXML
    private Label labelBreedHamster;

    @FXML
    private Label labelBreedRabbit;

    @FXML
    private Label labelDescribe;

    @FXML
    private Label labelEarLengthRabbit;

    @FXML
    private Label labelEyeColorCat;

    @FXML
    private Label labelFurColorGeneral;

    @FXML
    private Label labelGenderGeneral;

    @FXML
    private Label labelGiaBan;

    @FXML
    private Label labelHealthStatusGeneral;

    @FXML
    private Label labelIndoorCat;

    @FXML
    private Label labelNameGeneral;

    @FXML
    private Label labelOriginGeneral;

    @FXML
    private Label labelPetIDGeneral;

    @FXML
    private Label labelSupplierGeneral;

    @FXML
    private Label labelTailLengthHamster;

    @FXML
    private Label labelTrainedDog;

    @FXML
    private Label labelVaccinatedGeneral;

    @FXML
    private Label labelWeightGeneral;

    @FXML
    private ImageView logoImg;

    @FXML
    private TextField textSreach;

    @FXML
    void actionFind(ActionEvent event) {

    }

    @FXML
    void actionSetting(ActionEvent event) {

    }

    @FXML
    void actionUser(ActionEvent event) {

    }

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
        labelPetIDGeneral.setText(String.valueOf("PE" + obj.getId()));
        labelNameGeneral.setText(obj.getName());
        labelGiaBan.setText(String.valueOf(obj.getPrice() + "$"));
        labelDescribe.setText(obj.getDescription());
        labelAgeGeneral.setText(String.valueOf(obj.getAge() + "month"));
        String genderText;
        if (obj.isGender()) {
            genderText = "Male";
        } else {
            genderText = "Female";
        }
        labelGenderGeneral.setText(genderText);
        if (obj.isVaccinated()) {
            labelVaccinatedGeneral.setText("Injected");
        } else {
            labelVaccinatedGeneral.setText("Unvaccinated");
        }
        labelWeightGeneral.setText(String.valueOf(obj.getWeight()));
        labelOriginGeneral.setText(String.valueOf(obj.getOrigin()));
        labelHealthStatusGeneral.setText(obj.getHealthStatus());
        labelFurColorGeneral.setText(obj.getFurColor());
        labelSupplierGeneral.setText(String.valueOf(obj.getSupplier()));
        if (obj instanceof Dog) {
            Dog dog = (Dog) obj;
            labelBreedDog.setText(String.valueOf(dog.getBreed().getBreed()));
            if (dog.isIsTrained()) {
                labelTrainedDog.setText("Trained");
            } else {
                labelTrainedDog.setText("Untrained");
            }

            hideScreen();

            gridPaneDog.setVisible(true);
            gridPaneDog.setManaged(true);
        } else if (obj instanceof Cat) {
            Cat cat = (Cat) obj;
            labelBreedCat.setText(String.valueOf(cat.getBreed().getBreed()));
            if (cat.isIsIndoor()) {
                labelIndoorCat.setText("Indoor");
            } else {
                labelIndoorCat.setText("Unindoor");
            }
            labelEyeColorCat.setText(String.valueOf(cat.getEyeColor()));

            hideScreen();

            gridPaneCat.setVisible(true);
            gridPaneCat.setManaged(true);
        } else if (obj instanceof Hamster) {
            Hamster hamster = (Hamster) obj;
            labelBreedHamster.setText(String.valueOf(hamster.getBreed().getBreed()));
            labelTailLengthHamster.setText(String.valueOf(hamster.getTailLength()));

            hideScreen();
            gridPaneHamster.setVisible(true);
            gridPaneHamster.setManaged(true);
        } else if (obj instanceof Rabbit) {
            Rabbit rabbit = (Rabbit) obj;
            labelBreedRabbit.setText(String.valueOf(rabbit.getBreed().getBreed()));
            labelEarLengthRabbit.setText(String.valueOf(rabbit.getEarLength()));

            hideScreen();
            gridPaneRabbit.setVisible(true);
            gridPaneRabbit.setManaged(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hideScreen();


    }
}
