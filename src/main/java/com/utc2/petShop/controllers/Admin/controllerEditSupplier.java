package com.utc2.petShop.controllers.Admin;

import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.repository.UpdateById.UpdateSupplier;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
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

import java.net.URL;
import java.util.ResourceBundle;

public class controllerEditSupplier implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancel;

    @FXML
    private GridPane gridPaneGeneral;

    @FXML
    private TextField textFieldAddressGeneral;

    @FXML
    private TextField textFieldEmailGeneral;

    @FXML
    private TextField textFieldNameGeneral;

    @FXML
    private TextField textFieldPhoneNumberGeneral;

    @FXML
    void actionAdd(ActionEvent event) {
        String name = textFieldNameGeneral.getText();
        String email = textFieldEmailGeneral.getText();
        String phoneNumber = textFieldPhoneNumberGeneral.getText();
        String address = textFieldAddressGeneral.getText();

        UpdateSupplier.updateSupplier(supplier.getId(), name, email, phoneNumber, address);

        ((Stage) buttonCancel.getScene().getWindow()).close();


    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage)buttonCancel.getScene().getWindow()).close();
    }

    public void exceptions() {
        TextFormatter<String> formatterPhone = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            if (newText.isEmpty() || newText.matches("0\\d{0,9}")) {
                return change;
            } else {
                return null;
            }
        });
        textFieldPhoneNumberGeneral.setTextFormatter(formatterPhone);

        textFieldPhoneNumberGeneral.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 10) {
                textFieldPhoneNumberGeneral.setStyle("-fx-border-color: red;");
                buttonAddDisable();
            } else {
                textFieldPhoneNumberGeneral.setStyle(""); // xóa viền đỏ nếu hợp lệ
            }
        });

        TextFormatter<String> formatterEmail = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            if (newText.isEmpty() || newText.matches("[a-zA-Z0-9@._\\-]*")) {
                return change;
            } else {
                return null;
            }
        });
        textFieldEmailGeneral.setTextFormatter(formatterEmail);

        textFieldEmailGeneral.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
                textFieldEmailGeneral.setStyle("-fx-border-color: red;");
                buttonAddDisable();
            }
            else {
                textFieldEmailGeneral.setStyle("");
            }
        });


    }

    public void buttonAddDisable() {
        boolean isAnyFieldEmpty =
                textFieldNameGeneral.getText().isEmpty() ||
                        textFieldEmailGeneral.getText().isEmpty() ||
                        textFieldPhoneNumberGeneral.getText().isEmpty() ||
                        textFieldAddressGeneral.getText().isEmpty() ||
                        !textFieldEmailGeneral.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") ||
                        (textFieldPhoneNumberGeneral.getText().length() < 10);

        buttonAdd.setDisable(isAnyFieldEmpty);
    }

    public void setButtonAddDisable() {
        buttonAdd.setDisable(true);
        ChangeListener<String> listener = (observable, oldValue, newValue) -> buttonAddDisable();

        textFieldNameGeneral.textProperty().addListener(listener);
        textFieldEmailGeneral.textProperty().addListener(listener);
        textFieldPhoneNumberGeneral.textProperty().addListener(listener);
        textFieldAddressGeneral.textProperty().addListener(listener);
    }

    public void receiveData(Supplier obj){
        supplier = obj;
        textFieldNameGeneral.setText(obj.getName());
        textFieldAddressGeneral.setText(obj.getAddress());
        textFieldPhoneNumberGeneral.setText(obj.getPhoneNumber());
        textFieldEmailGeneral.setText(obj.getEmail());
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

        jumpOnEnter(textFieldNameGeneral,textFieldAddressGeneral);
        jumpOnEnter(textFieldAddressGeneral,textFieldPhoneNumberGeneral);
        jumpOnEnter(textFieldPhoneNumberGeneral,textFieldEmailGeneral);

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

    private static Supplier supplier;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exceptions();

        setButtonAddDisable();

        buttonEnter();
    }
}
