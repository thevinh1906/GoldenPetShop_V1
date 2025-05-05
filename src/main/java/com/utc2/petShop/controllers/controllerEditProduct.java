package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Product.*;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.repository.Select.SelectSupplier;
import com.utc2.petShop.model.repository.UpdateById.UpdateProduct;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class controllerEditProduct implements Initializable {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancel;

    @FXML
    private ChoiceBox<Product> choiceBoxPetSuppliesGeneral;

    @FXML
    private ComboBox<Supplier> comboBoxSupplierGeneral;

    @FXML
    private DatePicker datePickerExpirationDateFood;

    @FXML
    private GridPane gridPaneAccessory;

    @FXML
    private GridPane gridPaneCage;

    @FXML
    private GridPane gridPaneFood;

    @FXML
    private GridPane gridPaneGeneral;

    @FXML
    private GridPane gridPaneToy;

    @FXML
    private Spinner<Integer> spinnerQuantityGeneral;

    @FXML
    private TextArea textAreaDescriptionGeneral;

    @FXML
    private TextField textFieldDimensionCage;

    @FXML
    private TextField textFieldFlavorFood;

    @FXML
    private TextField textFieldManufacturerGeneral;

    @FXML
    private TextField textFieldMaterialCage;

    @FXML
    private TextField textFieldNameGeneral;

    @FXML
    private TextField textFieldPriceGeneral;

    @FXML
    private TextField textFieldBrandAccessory;

    @FXML
    private TextField textFieldTypeAccessory;

    @FXML
    private TextField textFieldMaterialToy;

    @FXML
    private TextField textFieldSizeToy;

    @FXML
    void actionAdd(ActionEvent event) {
        Supplier supplier = comboBoxSupplierGeneral.getValue();
        String name = textFieldNameGeneral.getText();
        double price = Double.parseDouble(textFieldPriceGeneral.getText());
        int quantity = spinnerQuantityGeneral.getValue();
        String description = textAreaDescriptionGeneral.getText();
        String manufacturer = textFieldManufacturerGeneral.getText();
        String role = String.valueOf(choiceBoxPetSuppliesGeneral.getValue());
        String type = "";
        String brand = "";
        LocalDate expirationDate = null;
        String flavor = "";
        String dimension = "";
        String material = "";
        String size = "";
        if(role.equals("Food")){
            expirationDate = datePickerExpirationDateFood.getValue();
            flavor = textFieldFlavorFood.getText();
        }
        else if(role.equals("Toy")){
            material = textFieldMaterialToy.getText();
            size = textFieldSizeToy.getText();
        }
        else if(role.equals("Cage")){
            material = textFieldMaterialCage.getText();
            dimension = textFieldDimensionCage.getText();
        }
        else if(role.equals("Accessory")){
            brand = textFieldBrandAccessory.getText();
            type = textFieldTypeAccessory.getText();
        }

        UpdateProduct.updateProduct(product.getId(),supplier,name,price,quantity,description,manufacturer,type,brand,expirationDate,flavor,dimension,manufacturer,size,role);
    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage)buttonCancel.getScene().getWindow()).close();
    }

    public void griPaneVision(){
        gridPaneToy.setVisible(false);
        gridPaneCage.setVisible(false);
        gridPaneFood.setVisible(false);
        gridPaneAccessory.setVisible(false);
        gridPaneToy.setManaged(false);
        gridPaneCage.setManaged(false);
        gridPaneFood.setManaged(false);
        gridPaneAccessory.setManaged(false);
    }

    public void insertPetSupplies() {
        choiceBoxPetSuppliesGeneral.getItems().addAll(new Food(), new Toy(), new Cage(), new Accessory());

        choiceBoxPetSuppliesGeneral.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof Food) {
                griPaneVision();
                gridPaneFood.setVisible(true);
                gridPaneFood.setManaged(true);
            }
            else if (newValue instanceof Toy) {
                griPaneVision();
                gridPaneToy.setVisible(true);
                gridPaneToy.setManaged(true);
            }else if (newValue instanceof Cage) {
                griPaneVision();
                gridPaneCage.setVisible(true);
                gridPaneCage.setManaged(true);
            }else if (newValue instanceof Accessory) {
                griPaneVision();
                gridPaneAccessory.setVisible(true);
                gridPaneAccessory.setManaged(true);
            }
            else {
                griPaneVision();
            }
        });
    }

    public void insertQuantity(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000,1);

        spinnerQuantityGeneral.setValueFactory(valueFactory);

        spinnerQuantityGeneral.setEditable(true);

        TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), 1,
                change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("\\d*")) { // chỉ cho chữ số (0-9)
                        return change;
                    }
                    return null;
                });
        spinnerQuantityGeneral.getEditor().setTextFormatter(formatter);
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
    }

    public void receiveData(Product obj){
        product = obj;
        textFieldNameGeneral.setText(obj.getName());
        spinnerQuantityGeneral.getValueFactory().setValue(obj.getQuantity());
        textFieldPriceGeneral.setText(String.valueOf(obj.getPrice()));
        comboBoxSupplierGeneral.setValue(obj.getSupplier());
        textAreaDescriptionGeneral.setText(obj.getDescription());
        textFieldManufacturerGeneral.setText(obj.getManufacturer());
        if(obj instanceof Food){
            Food food = (Food) obj;
            choiceBoxPetSuppliesGeneral.setValue(food);
            datePickerExpirationDateFood.setValue(food.getExpirationDate());
            textFieldFlavorFood.setText(food.getFlavor());
        }
        else if(obj instanceof Toy){
            Toy toy = (Toy) obj;
            choiceBoxPetSuppliesGeneral.setValue(toy);
            textFieldMaterialToy.setText(toy.getMaterial());
            textFieldSizeToy.setText(toy.getSize());
        }
        else if(obj instanceof Cage){
            Cage cage = (Cage) obj;
            choiceBoxPetSuppliesGeneral.setValue(cage);
            textFieldMaterialCage.setText(cage.getMaterial());
            textFieldDimensionCage.setText(cage.getDimension());
        }
        else if(obj instanceof Accessory){
            Accessory accessory = (Accessory) obj;
            choiceBoxPetSuppliesGeneral.setValue(accessory);
            textFieldTypeAccessory.setText(accessory.getType());
            textFieldBrandAccessory.setText(accessory.getBrand());
        }
    }

    private static ObservableList<Supplier> listSupplier = FXCollections.observableArrayList(SelectSupplier.getAllSuppliers());

    Product product;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        griPaneVision();

        insertPetSupplies();

        insertQuantity();

        exceptions();

        comboBoxSupplierGeneral.setItems(listSupplier);
    }
}
