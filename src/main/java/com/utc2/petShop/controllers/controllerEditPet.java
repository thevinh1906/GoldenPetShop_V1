package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Pet.*;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerEditPet implements Initializable {

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

    String priceExceptions = "\\d*(\\.\\d*)?";

    @FXML
    void actionChange(ActionEvent event) {

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

    public void insertAnimal(){
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

    public void insertAge(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999,1);

        spinnerAgeGeneral.setValueFactory(valueFactory);

        spinnerAgeGeneral.setEditable(true);

        TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), 1,
                change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("\\d*")) { // chỉ cho chữ số (0-9)
                        return change;
                    }
                    return null;
                });
        spinnerAgeGeneral.getEditor().setTextFormatter(formatter);
    }

    public void exceptions(){
        TextFormatter<String> formatterPrice = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.matches("\\d*(\\.\\d{0,2})?")) {
                return change;
            } else {
                return null;
            }
        });
        textFieldPriceGeneral.setTextFormatter(formatterPrice);
        TextFormatter<String> formatterWeight = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.matches("\\d*(\\.\\d{0,2})?")) {
                return change;
            } else {
                return null;
            }
        });
        textFieldWeightGeneral.setTextFormatter(formatterWeight);
    }

    public void receiveData(Pet obj) {
        textFieldNameGeneral.setText(obj.getName());
        spinnerAgeGeneral.getValueFactory().setValue(obj.getAge());
        if(obj.isGender()){
            radioButtonMaleGeneral.setSelected(true);
        }
        else{
            radioButtonFemaleGeneral.setSelected(true);
        }
        textFieldPriceGeneral.setText(String.valueOf(obj.getPrice()));
        checkBoxVaccinatedGeneral.setSelected(obj.isVaccinated());
        textFieldHealthStatusGeneral.setText(obj.getHealthStatus());
        textFieldOriginGeneral.setText(obj.getOrigin());
        textFieldWeightGeneral.setText(String.valueOf(obj.getWeight()));
        textFieldFurColorGeneral.setText(obj.getFurColor());
        comboBoxSupplierGeneral.setValue(obj.getSupplier());
        textAreaDescriptionGeneral.setText(obj.getDescription());
        if(obj instanceof Dog){
            Dog dog = (Dog)obj;
            choiceBoxAnimalGeneral.setValue(dog);
            comboBoxBreedDog.setValue(dog.getBreed());
            checkBoxTrainedDog.setSelected(dog.isIsTrained());
        }
        else if(obj instanceof Cat){
            Cat cat = (Cat)obj;
            choiceBoxAnimalGeneral.setValue(cat);
            comboBoxBreedCat.setValue(cat.getBreed());
            checkBoxIndoorCat.setSelected(cat.isIsIndoor());
            textFieldEyeColorCat.setText(cat.getEyeColor());
        }
        else if(obj instanceof Hamster){
            Hamster hamster = (Hamster)obj;
            choiceBoxAnimalGeneral.setValue(hamster);
            comboBoxBreedHamster.setValue(hamster.getBreed());
            texFieldTailLengthHamster.setText(String.valueOf(hamster.getTailLength()));
        }
        else if(obj instanceof Rabbit){
            Rabbit rabbit = (Rabbit)obj;
            choiceBoxAnimalGeneral.setValue(rabbit);
            comboBoxBreedRabbit.setValue(rabbit.getBreed());
            textFieldEarLengthRabbit.setText(String.valueOf(rabbit.getEarLength()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        griPaneVision();

        insertAnimal();

        insertAge();

        exceptions();


    }

}
