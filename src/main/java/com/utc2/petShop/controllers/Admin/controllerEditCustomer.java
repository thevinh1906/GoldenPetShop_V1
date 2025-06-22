package com.utc2.petShop.controllers.Admin;

import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.repository.UpdateById.UpdateCustomer;
import javafx.application.Platform;
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

public class controllerEditCustomer implements Initializable {

    @FXML
    private BorderPane root;

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

        ((Stage) buttonCancel.getScene().getWindow()).close();

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
            if (newValue.length() < 10) {
                textFieldPhoneNumberGeneral.setStyle("-fx-border-color: red;");
                buttonAddDisable();
            } else {
                textFieldPhoneNumberGeneral.setStyle(""); // xóa viền đỏ nếu hợp lệ
            }
        });
    }

    public void buttonAddDisable() {
        boolean isAnyFieldEmpty = textFieldNameGeneral.getText().isEmpty() || textFieldPhoneNumberGeneral.getText().isEmpty() || (textFieldPhoneNumberGeneral.getText().length() < 10);
        buttonChange.setDisable(isAnyFieldEmpty);
    }

    public void setButtonAddDisable(){
        buttonChange.setDisable(true);
        textFieldNameGeneral.textProperty().addListener((observable, oldValue, newValue) -> buttonAddDisable());
        textFieldPhoneNumberGeneral.textProperty().addListener((observable, oldValue, newValue) -> buttonAddDisable());
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

        jumpOnEnter(textFieldNameGeneral, textFieldPhoneNumberGeneral);

        Platform.runLater(() -> {
            Scene scene = root.getScene();
            if (scene != null) {
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        buttonChange.fire();
                    }
                });
            }
        });
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

        buttonEnter();
    }
}
