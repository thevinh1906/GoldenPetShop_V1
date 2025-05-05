package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Product.Product;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.repository.UpdateById.UpdateSupplier;
import javafx.application.Platform;
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

public class controllerEditSupplier implements Initializable {

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

    public void exceptions(){
        TextFormatter<String> formatterPhone = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            if (newText.isEmpty() || newText.matches("0\\d{0,10}")) {
                return change;
            } else {
                return null;
            }
        });
        textFieldPhoneNumberGeneral.setTextFormatter(formatterPhone);


        TextFormatter<String> formatterEmail = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Cho phép nhập trống, hoặc email có ký tự hợp lệ
            if (newText.matches("[a-zA-Z0-9@._\\-]*") || newText.isEmpty()) {
                return change;
            } else {
                return null;
            }
        });

        textFieldEmailGeneral.setTextFormatter(formatterEmail);



    }

    public void receiveData(Supplier obj){
        supplier = obj;
        textFieldNameGeneral.setText(obj.getName());
        textFieldAddressGeneral.setText(obj.getAddress());
        textFieldPhoneNumberGeneral.setText(obj.getPhoneNumber());
        textFieldEmailGeneral.setText(obj.getEmail());
    }

    private static Supplier supplier;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exceptions();
    }
}
