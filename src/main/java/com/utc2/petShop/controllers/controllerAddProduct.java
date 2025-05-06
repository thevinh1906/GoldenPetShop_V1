package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Product.*;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.repository.Insert.InsertProduct;
import com.utc2.petShop.model.repository.Select.SelectSupplier;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class controllerAddProduct implements Initializable {

    @FXML
    private BorderPane root;

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
    private TextField textFielldBrandAccessory;

    @FXML
    private TextField textFielldTypeAccessory;

    @FXML
    private TextField textFiieldMaterialToy;

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
        if (role.equals("Food")) {
            expirationDate = datePickerExpirationDateFood.getValue();
            flavor = textFieldFlavorFood.getText();
        } else if (role.equals("Toy")) {
            material = textFiieldMaterialToy.getText();
            size = textFieldSizeToy.getText();
        } else if (role.equals("Cage")) {
            material = textFieldMaterialCage.getText();
            dimension = textFieldDimensionCage.getText();
        } else if (role.equals("Accessory")) {
            brand = textFielldBrandAccessory.getText();
            type = textFielldTypeAccessory.getText();
        }
        InsertProduct.insertProduct(supplier, name, price, quantity, description, manufacturer, type, brand, expirationDate, flavor, dimension, material, size, role);

        ((Stage) buttonCancel.getScene().getWindow()).close();

    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage) buttonCancel.getScene().getWindow()).close();
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
                        textFiieldMaterialToy.getText().isEmpty() ||
                                textFieldSizeToy.getText().isEmpty()  || !textFieldSizeToy.getText().matches("\\d{1,4}x\\d{1,4}x\\d{1,4}");
            }
            case "Cage" -> {
                isAnyFieldEmpty |=
                        textFieldMaterialCage.getText().isEmpty() ||
                                textFieldDimensionCage.getText().isEmpty() || !textFieldDimensionCage.getText().matches("\\d{1,4}x\\d{1,4}x\\d{1,4}");
            }
            case "Accessory" -> {
                isAnyFieldEmpty |=
                        textFielldBrandAccessory.getText().isEmpty() ||
                                textFielldTypeAccessory.getText().isEmpty();
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
        textFiieldMaterialToy.textProperty().addListener((obs, o, n) -> buttonAddDisable());

        textFielldBrandAccessory.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        textFielldTypeAccessory.textProperty().addListener((obs, o, n) -> buttonAddDisable());

        // Kiểm tra lần đầu khi giao diện hiển thị
    }



    public void griPaneVision() {
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
            } else if (newValue instanceof Toy) {
                griPaneVision();
                gridPaneToy.setVisible(true);
                gridPaneToy.setManaged(true);
            } else if (newValue instanceof Cage) {
                griPaneVision();
                gridPaneCage.setVisible(true);
                gridPaneCage.setManaged(true);
            } else if (newValue instanceof Accessory) {
                griPaneVision();
                gridPaneAccessory.setVisible(true);
                gridPaneAccessory.setManaged(true);
            } else {
                griPaneVision();
            }
        });
    }

    public void insertQuantity() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000, 1);

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

    public void exceptions() {
        TextFormatter<String> formatterPrice = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.matches("\\d{0,10}(\\.\\d{0,2})?")) {
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
                textFieldDimensionCage.setStyle("-fx-border-color: red;");
                buttonAddDisable();
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
                textFieldSizeToy.setStyle("-fx-border-color: red;");
                buttonAddDisable();
            } else {
                textFieldSizeToy.setStyle(""); // Hợp lệ -> xóa border đỏ
            }
        });
    }

    private void jumpOnEnter(Control current, Control next) {
        current.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Trường hợp đặc biệt với TextArea: Enter là xuống dòng
                if (!(current instanceof TextArea)) {
                    next.requestFocus();
                }
            }
        });
    }

    public void buttonEnter() {

        jumpOnEnter(textFieldNameGeneral,spinnerQuantityGeneral);
        jumpOnEnter(spinnerQuantityGeneral,textFieldPriceGeneral);
        jumpOnEnter(textFieldPriceGeneral,comboBoxSupplierGeneral);
        jumpOnEnter(choiceBoxPetSuppliesGeneral,textAreaDescriptionGeneral);
        jumpOnEnter(textFieldManufacturerGeneral,choiceBoxPetSuppliesGeneral);

        choiceBoxPetSuppliesGeneral.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof Food) {
                jumpOnEnter(choiceBoxPetSuppliesGeneral,datePickerExpirationDateFood);
                jumpOnEnter(datePickerExpirationDateFood,textFieldFlavorFood);
            }
            else if (newValue instanceof Toy) {
                jumpOnEnter(choiceBoxPetSuppliesGeneral,textFiieldMaterialToy);
                jumpOnEnter(textFiieldMaterialToy,textFieldSizeToy);
            }
            else if (newValue instanceof Cage) {
                jumpOnEnter(choiceBoxPetSuppliesGeneral,textFieldMaterialCage);
                jumpOnEnter(textFieldMaterialCage,textFieldDimensionCage);
            }
            else if (newValue instanceof Accessory) {
                jumpOnEnter(choiceBoxPetSuppliesGeneral,textFielldTypeAccessory);
                jumpOnEnter(textFielldTypeAccessory,textFielldBrandAccessory);
            }
        });

        Platform.runLater(() -> {
            Scene scene = root.getScene();
            if (scene != null) {
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        buttonAdd.fire();
                    }
                });
            }
        });
    }

    private static ObservableList<Supplier> listSupplier = FXCollections.observableArrayList(SelectSupplier.getAllSuppliers());

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        griPaneVision();

        insertPetSupplies();

        insertQuantity();

        exceptions();

        comboBoxSupplierGeneral.setItems(listSupplier);

        setButtonAddDisable();

        buttonEnter();
    }
}
