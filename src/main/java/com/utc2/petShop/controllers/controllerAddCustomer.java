package com.utc2.petShop.controllers;

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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exceptions();

    }
}
