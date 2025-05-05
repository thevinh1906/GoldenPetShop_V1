package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Pet.*;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.repository.Select.SelectSupplier;
import com.utc2.petShop.model.repository.UpdateById.UpdatePet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        String name = textFieldNameGeneral.getText();
        int age = spinnerAgeGeneral.getValue();
        boolean gender = !radioButtonFemaleGeneral.isSelected();
        double price = Double.parseDouble(textFieldPriceGeneral.getText());
        boolean vaccinated = checkBoxVaccinatedGeneral.isSelected();
        String healthStatus = textFieldHealthStatusGeneral.getText();
        String origin  = textFieldOriginGeneral.getText();
        double weight = Double.parseDouble(textFieldWeightGeneral.getText());
        String furColor = textFieldFurColorGeneral.getText();
        String description = textAreaDescriptionGeneral.getText();
        Supplier supplier = comboBoxSupplierGeneral.getValue();
        String role = String.valueOf(choiceBoxAnimalGeneral.getValue());
        Boolean isIndoor = false;
        String breed = "";
        String eyeColor = "";
        boolean isTrained = false;
        float tailLength = 0;
        float earLength = 0;
        if(role.equals("Cat")){
            breed = String.valueOf(comboBoxBreedCat.getValue());
            eyeColor = textFieldEyeColorCat.getText();
            isIndoor = checkBoxIndoorCat.isSelected();
        }
        else if(role.equals("Dog")){
            breed = String.valueOf(comboBoxBreedDog.getValue());
            isTrained = checkBoxTrainedDog.isSelected();

        }
        else if(role.equals("Hamster")){
            breed = String.valueOf(comboBoxBreedHamster.getValue());
            tailLength = Float.parseFloat(texFieldTailLengthHamster.getText());

        }
        else if(role.equals("Rabbit")){
            breed = String.valueOf(comboBoxBreedRabbit.getValue());
            earLength = Float.parseFloat(textFieldEarLengthRabbit.getText());

        }

        UpdatePet.updatePet(pet.getId(),name,age,gender,price,vaccinated,healthStatus,origin,weight,furColor,description,supplier,role,isIndoor,breed,eyeColor,isTrained,tailLength,earLength);
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

    public void buttonAddDisable(){
        String name = textFieldNameGeneral.getText();
        String priceText = textFieldPriceGeneral.getText();
        String healthStatus = textFieldHealthStatusGeneral.getText();
        String origin = textFieldOriginGeneral.getText();
        String weightText = textFieldWeightGeneral.getText();
        String furColor = textFieldFurColorGeneral.getText();
        String description = textAreaDescriptionGeneral.getText();
        Supplier supplier = comboBoxSupplierGeneral.getValue();
        Pet pet = choiceBoxAnimalGeneral.getValue();
        String role = String.valueOf(choiceBoxAnimalGeneral.getValue());

        boolean isAnyFieldEmpty =
                name.isEmpty() ||
                        priceText.isEmpty() ||
                        healthStatus.isEmpty() ||
                        origin.isEmpty() ||
                        weightText.isEmpty() ||
                        furColor.isEmpty() ||
                        description.isEmpty() ||
                        supplier == null ||
                        role.equals("null");

        // Kiểm tra các trường riêng theo từng loại động vật
        if (pet instanceof Cat) {
            isAnyFieldEmpty |=
                    comboBoxBreedCat.getValue() == null ||
                            textFieldEyeColorCat.getText().isEmpty();
        } else if (pet instanceof Dog) {
            isAnyFieldEmpty |=
                    comboBoxBreedDog.getValue() == null;
        } else if (pet instanceof Hamster) {
            isAnyFieldEmpty |=
                    comboBoxBreedHamster.getValue() == null ||
                            texFieldTailLengthHamster.getText().isEmpty();
        } else if (pet instanceof Rabbit) {
            isAnyFieldEmpty |=
                    comboBoxBreedRabbit.getValue() == null ||
                            textFieldEarLengthRabbit.getText().isEmpty();
        }

        buttonAdd.setDisable(isAnyFieldEmpty);
    }

    public void setButtonAddDisable(){
        textFieldNameGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldPriceGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldHealthStatusGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldOriginGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldWeightGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldFurColorGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textAreaDescriptionGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        comboBoxSupplierGeneral.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        choiceBoxAnimalGeneral.valueProperty().addListener((obs, oldVal, newVal) -> {
//            updateAnimalSpecificFields(); // nếu bạn thay đổi loại thú, cần gọi lại logic hiển thị trường phù hợp
            buttonAddDisable();
        });

        // Các trường riêng theo loại thú cưng
        comboBoxBreedCat.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldEyeColorCat.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        comboBoxBreedDog.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        comboBoxBreedHamster.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        texFieldTailLengthHamster.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        comboBoxBreedRabbit.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldEarLengthRabbit.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
    }

    public void receiveData(Pet obj) {
        pet = obj;
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

    private static Pet pet;

    private static ObservableList<Supplier> listSupplier = FXCollections.observableArrayList(SelectSupplier.getAllSuppliers());

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        griPaneVision();

        insertAnimal();

        insertAge();

        exceptions();

        comboBoxSupplierGeneral.setItems(listSupplier);

        comboBoxBreedDog.getItems().addAll(EDogBreed.values());

        comboBoxBreedCat.getItems().addAll(ECatBreed.values());

        comboBoxBreedHamster.getItems().addAll(EHamsterBreed.values());

        comboBoxBreedRabbit.getItems().addAll(ERabbitBreed.values());

        setButtonAddDisable();

    }

}
