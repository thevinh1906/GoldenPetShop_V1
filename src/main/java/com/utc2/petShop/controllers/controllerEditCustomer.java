package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.repository.UpdateById.UpdateCustomer;
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

public class controllerEditCustomer implements Initializable {

    @FXML
    private Button buttonChange;

    @FXML
    private Button buttonCancel;

    @FXML
    private GridPane gridPaneGeneral;

    @FXML
    private TextField textFieldNameGeneral;

    @FXML
    private TextField textFieldPhoneNumberGeneral;

    @FXML
    void actionChange(ActionEvent event) {
        UpdateCustomer.updateCustomer(customer.getId(), textFieldNameGeneral.getText(), textFieldPhoneNumberGeneral.getText());
    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage)buttonCancel.getScene().getWindow()).close();
    }

    private static Customer customer;

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
            buttonChange.setDisable(newValue.length() < 10);
            if (newValue.length() < 10) {
                textFieldPhoneNumberGeneral.setStyle("-fx-border-color: red;");
            } else {
                textFieldPhoneNumberGeneral.setStyle(""); // xóa viền đỏ nếu hợp lệ
            }
        });
    }

    public void buttonAddDisable() {
        boolean isAnyFieldEmpty = textFieldNameGeneral.getText().isEmpty() || textFieldPhoneNumberGeneral.getText().isEmpty();
        buttonChange.setDisable(isAnyFieldEmpty);
    }

    public void setButtonAddDisable(){
        buttonChange.setDisable(true);
        textFieldNameGeneral.textProperty().addListener((observable, oldValue, newValue) -> buttonAddDisable());
        textFieldPhoneNumberGeneral.textProperty().addListener((observable, oldValue, newValue) -> buttonAddDisable());
    }

    public void receiveData(Customer obj){
        customer = obj;
        textFieldNameGeneral.setText(obj.getName());
        textFieldPhoneNumberGeneral.setText(obj.getPhoneNumber());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exceptions();

        setButtonAddDisable();
    }
}
