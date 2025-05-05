package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.User.EEmployeePosition;
import com.utc2.petShop.model.repository.Insert.InsertUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class controllerAddUser implements Initializable {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancel;

    @FXML
    private ChoiceBox<EEmployeePosition> choiceBoxPositionGeneral;

    @FXML
    private DatePicker datePickerBirthDateGeneral;

    @FXML
    private DatePicker datePickerCreationDateGeneral;

    @FXML
    private ToggleGroup gender;

    @FXML
    private GridPane gridPaneGeneral;

    @FXML
    private RadioButton radioButtonFemaleGeneral;

    @FXML
    private RadioButton radioButtonMaleGeneral;

    @FXML
    private TextField textFieldAddressGeneral;

    @FXML
    private TextField textFieldEmailGeneral;

    @FXML
    private TextField textFieldNameGeneral;

    @FXML
    private TextField textFieldPasswordGeneral;

    @FXML
    private TextField textFieldPhoneNumberGeneral;

    @FXML
    private TextField textFieldSalaryGeneral;

    @FXML
    private TextField textFieldUsernameGeneral;

    @FXML
    private TextField textFieldWorkingHoursGeneral;

    @FXML
    void actionAdd(ActionEvent event) {
        String username = textFieldUsernameGeneral.getText();
        String password = textFieldPasswordGeneral.getText();
        String name = textFieldNameGeneral.getText();
        boolean gender = !radioButtonFemaleGeneral.isSelected();
        String email = textFieldEmailGeneral.getText();
        String phoneNumber = textFieldPhoneNumberGeneral.getText();
        String address = textFieldAddressGeneral.getText();
        LocalDate birthDay = datePickerBirthDateGeneral.getValue();
        LocalDate creationDate = datePickerCreationDateGeneral.getValue();
        String position = String.valueOf(choiceBoxPositionGeneral.getValue());
        System.out.println(position);
        float salary = 0;
        String workingHours = textFieldWorkingHoursGeneral.getText();
        String role = "";
        if(!position.equals("null")){
            role = "Employee";
            salary = Float.parseFloat(textFieldSalaryGeneral.getText());
        }

        InsertUser.insertUser(username,password,name,gender,email,phoneNumber,address,birthDay,creationDate,position,salary,workingHours,role);
    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage) buttonCancel.getScene().getWindow()).close();
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

        TextFormatter<String> formatterSalary = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.matches("\\d*(\\.\\d{0,2})?")) {
                return change;
            } else {
                return null;
            }
        });

        textFieldSalaryGeneral.setTextFormatter(formatterSalary);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        datePickerCreationDateGeneral.setValue(LocalDate.now());

        exceptions();

        choiceBoxPositionGeneral.getItems().addAll(EEmployeePosition.values());
    }
}
