package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.entities.User.Admin;
import com.utc2.petShop.model.entities.User.EEmployeePosition;
import com.utc2.petShop.model.entities.User.Employee;
import com.utc2.petShop.model.entities.User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class controllerEditUser implements Initializable {

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

    public void receiveData(User obj){
        textFieldUsernameGeneral.setText(obj.getUsername());
        textFieldPasswordGeneral.setText(obj.getPassword());
        textFieldNameGeneral.setText(obj.getName());
        if(obj.isGender()){
            radioButtonMaleGeneral.setSelected(true);
        }
        else {
            radioButtonFemaleGeneral.setSelected(true);
        }
        textFieldEmailGeneral.setText(obj.getEmail());
        textFieldPhoneNumberGeneral.setText(obj.getPhoneNumber());
        textFieldAddressGeneral.setText(obj.getAddress());
        datePickerBirthDateGeneral.setValue(obj.getBirthDay());
        datePickerCreationDateGeneral.setValue(obj.getCreationDate());
        if (obj instanceof Employee){
            Employee employee = (Employee) obj;
            choiceBoxPositionGeneral.setValue(employee.getPosition());
            textFieldSalaryGeneral.setText(String.valueOf(employee.getSalary()));
            textFieldWorkingHoursGeneral.setText(String.valueOf(employee.getWorkingHours()));
        }
        else if (obj instanceof Admin){
            Admin admin = (Admin) obj;
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        datePickerCreationDateGeneral.setValue(LocalDate.now());

        exceptions();

        choiceBoxPositionGeneral.getItems().addAll(EEmployeePosition.values());

    }
}
