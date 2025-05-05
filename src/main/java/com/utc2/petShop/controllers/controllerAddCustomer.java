package com.utc2.petShop.controllers;

import com.utc2.petShop.model.repository.Insert.InsertCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerAddCustomer implements Initializable {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancel;

    @FXML
    private GridPane gridPaneGeneral;

    @FXML
    private TextField textFieldNameGeneral;

    @FXML
    private TextField textFieldPhoneNumberGeneral;

    @FXML
    void actionAdd(ActionEvent event) {
        InsertCustomer.insertCustomer(textFieldPhoneNumberGeneral.getText(), textFieldNameGeneral.getText());
    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage)buttonCancel.getScene().getWindow()).close();
    }

    public void exceptions(){
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
            buttonAdd.setDisable(newValue.length() < 10);
            if (newValue.length() < 10) {
                textFieldPhoneNumberGeneral.setStyle("-fx-border-color: red;");
            } else {
                textFieldPhoneNumberGeneral.setStyle(""); // xóa viền đỏ nếu hợp lệ
            }
        });

    }

    public void buttonAddDisable() {
        boolean isAnyFieldEmpty = textFieldNameGeneral.getText().isEmpty() || textFieldPhoneNumberGeneral.getText().isEmpty();
        buttonAdd.setDisable(isAnyFieldEmpty);
    }

    public void setButtonAddDisable(){
        buttonAdd.setDisable(true);
        textFieldNameGeneral.textProperty().addListener((observable, oldValue, newValue) -> buttonAddDisable());
        textFieldPhoneNumberGeneral.textProperty().addListener((observable, oldValue, newValue) -> buttonAddDisable());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exceptions();

        setButtonAddDisable();
    }
}
