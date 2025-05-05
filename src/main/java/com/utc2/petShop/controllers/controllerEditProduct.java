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

        ((Stage) buttonCancel.getScene().getWindow()).close();

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

        datePickerExpirationDateFood.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (!empty && !date.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // màu hồng nhạt cho ngày bị vô hiệu hóa
                }
            }
        });
        TextFormatter<String> formatterDimensionCage = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.isEmpty() || newText.matches("\\d{0,4}(x\\d{0,4}){0,2}")) {
                return change;
            }
            return null;
        });
        textFieldDimensionCage.setTextFormatter(formatterDimensionCage);

        textFieldDimensionCage.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{1,4}x\\d{1,4}x\\d{1,4}")) {
                buttonAdd.setDisable(true);
                textFieldDimensionCage.setStyle("-fx-border-color: red;");
            } else {
                textFieldDimensionCage.setStyle(""); // Hợp lệ -> xóa border đỏ
            }
        });


        TextFormatter<String> formatterSizeToy = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.isEmpty() || newText.matches("\\d{0,4}(x\\d{0,4}){0,2}")) {
                return change;
            }
            return null;
        });
        textFieldSizeToy.setTextFormatter(formatterSizeToy);

        textFieldSizeToy.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{1,4}x\\d{1,4}x\\d{1,4}")) {
                buttonAdd.setDisable(true);
                textFieldSizeToy.setStyle("-fx-border-color: red;");
            } else {
                textFieldSizeToy.setStyle(""); // Hợp lệ -> xóa border đỏ
            }
        });
    }

    public void buttonAddDisable() {
        boolean isAnyFieldEmpty =
                comboBoxSupplierGeneral.getValue() == null ||
                        textFieldNameGeneral.getText().isEmpty() ||
                        textFieldPriceGeneral.getText().isEmpty() ||
                        textAreaDescriptionGeneral.getText().isEmpty() ||
                        textFieldManufacturerGeneral.getText().isEmpty() ||
                        choiceBoxPetSuppliesGeneral.getValue() == null;

        String role = String.valueOf(choiceBoxPetSuppliesGeneral.getValue());

        switch (role) {
            case "Food" -> {
                isAnyFieldEmpty |=
                        datePickerExpirationDateFood.getValue() == null ||
                                textFieldFlavorFood.getText().isEmpty();
            }
            case "Toy" -> {
                isAnyFieldEmpty |=
                        textFieldMaterialToy.getText().isEmpty() ||
                                textFieldSizeToy.getText().isEmpty();
            }
            case "Cage" -> {
                isAnyFieldEmpty |=
                        textFieldMaterialCage.getText().isEmpty() ||
                                textFieldDimensionCage.getText().isEmpty();
            }
            case "Accessory" -> {
                isAnyFieldEmpty |=
                        textFieldBrandAccessory.getText().isEmpty() ||
                                textFieldTypeAccessory.getText().isEmpty();
            }
        }

        buttonAdd.setDisable(isAnyFieldEmpty);
    }

    public void setButtonAddDisable() {
        buttonAdd.setDisable(true);

        // Gắn listener cho các trường chính
        textFieldNameGeneral.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        textFieldPriceGeneral.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        textAreaDescriptionGeneral.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        textFieldManufacturerGeneral.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        comboBoxSupplierGeneral.valueProperty().addListener((obs, o, n) -> buttonAddDisable());
        choiceBoxPetSuppliesGeneral.valueProperty().addListener((obs, o, n) -> buttonAddDisable());

        // Các trường theo từng loại (bạn nên kiểm tra role để chọn listener cần gắn)
        textFieldFlavorFood.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        datePickerExpirationDateFood.valueProperty().addListener((obs, o, n) -> buttonAddDisable());

        textFieldMaterialCage.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        textFieldDimensionCage.textProperty().addListener((obs, o, n) -> buttonAddDisable());

        textFieldSizeToy.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        textFieldMaterialToy.textProperty().addListener((obs, o, n) -> buttonAddDisable());

        textFieldBrandAccessory.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        textFieldTypeAccessory.textProperty().addListener((obs, o, n) -> buttonAddDisable());

        // Kiểm tra lần đầu khi giao diện hiển thị
        buttonAddDisable();
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

        setButtonAddDisable();
    }
}
