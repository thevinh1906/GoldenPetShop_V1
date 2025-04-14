package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Pet.*;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerAddPet implements Initializable {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancel;

    @FXML
    private CheckBox checkBoxIndoorCat;

    @FXML
    private CheckBox checkBoxTrainedDog;

    @FXML
    private CheckBox checkBoxVaccinatedGeneral;

    @FXML
    private ChoiceBox<Pet> choiceBoxAnimalGeneral;

    @FXML
    private ComboBox<ECatBreed> comboBoxBreedCat;

    @FXML
    private ComboBox<EDogBreed> comboBoxBreedDog;

    @FXML
    private ComboBox<EHamsterBreed> comboBoxBreedHamster;

    @FXML
    private ComboBox<ERabbitBreed> comboBoxBreedRabbit;

    @FXML
    private ComboBox<Supplier> comboBoxSupplierGeneral;

    @FXML
    private ToggleGroup gender;

    @FXML
    private GridPane gridPaneCat;

    @FXML
    private GridPane gridPaneDog;

    @FXML
    private GridPane gridPaneHamster;

    @FXML
    private GridPane gridPaneRabbit;

    @FXML
    private RadioButton radioButtonFemaleGeneral;

    @FXML
    private RadioButton radioButtonMaleGeneral;

    @FXML
    private Spinner<Integer> spinnerAgeGeneral;

    @FXML
    private TextField texFieldTailLengthHamster;

    @FXML
    private TextArea textAreaDescriptionGeneral;

    @FXML
    private TextField textFieldEarLengthRabbit;

    @FXML
    private TextField textFieldEyeColorCat;

    @FXML
    private TextField textFieldFurColorGeneral;

    @FXML
    private TextField textFieldHealthStatusGeneral;

    @FXML
    private TextField textFieldNameGeneral;

    @FXML
    private TextField textFieldOriginGeneral;

    @FXML
    private TextField textFieldPriceGeneral;

    @FXML
    private TextField textFieldWeightGeneral;

    @FXML
    void actionAdd(ActionEvent event) {

    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage)buttonCancel.getScene().getWindow()).close();
    }

    public void griPaneVision(){
        gridPaneDog.setVisible(false);
        gridPaneCat.setVisible(false);
        gridPaneHamster.setVisible(false);
        gridPaneRabbit.setVisible(false);
        gridPaneDog.setManaged(false);
        gridPaneCat.setManaged(false);
        gridPaneHamster.setManaged(false);
        gridPaneRabbit.setManaged(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        griPaneVision();
        choiceBoxAnimalGeneral.getItems().addAll(new Dog(), new Cat(), new Hamster(), new Rabbit());

        choiceBoxAnimalGeneral.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                if(newValue instanceof Dog){
                    griPaneVision();
                    gridPaneDog.setVisible(true);
                    gridPaneDog.setManaged(true);
                }
                else if(newValue instanceof Cat){
                    griPaneVision();
                    gridPaneCat.setVisible(true);
                    gridPaneCat.setManaged(true);
                }
                else if(newValue instanceof Hamster){
                    griPaneVision();
                    gridPaneHamster.setVisible(true);
                    gridPaneHamster.setManaged(true);
                }
                else if(newValue instanceof Rabbit){
                    griPaneVision();
                    gridPaneRabbit.setVisible(true);
                    gridPaneRabbit.setManaged(true);
                }
            }
            else {
                griPaneVision();
            }
        });

    }

}
