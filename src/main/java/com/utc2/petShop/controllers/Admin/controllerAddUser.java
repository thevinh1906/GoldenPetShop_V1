package com.utc2.petShop.controllers.Admin;

import com.utc2.petShop.model.entities.User.EEmployeePosition;
import com.utc2.petShop.model.repository.Insert.InsertUser;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class controllerAddUser implements Initializable {

    @FXML
    private BorderPane root;

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
        if(position != null && !position.equals("null")){
            role = "Employee";
            salary = Float.parseFloat(textFieldSalaryGeneral.getText());
        }

        InsertUser.insertUser(username,password,name,gender,email,phoneNumber,address,birthDay,creationDate,position,salary,workingHours,role);

        ((Stage) buttonCancel.getScene().getWindow()).close();

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

        datePickerBirthDateGeneral.setValue(datePickerCreationDateGeneral.getValue().minusYears(18));
        datePickerBirthDateGeneral.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (!empty && date.isAfter(datePickerCreationDateGeneral.getValue().minusYears(18))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // đỏ nhạt cho ngày không hợp lệ
                }
            }
        });

        textFieldSalaryGeneral.setDisable(true);
        textFieldWorkingHoursGeneral.setDisable(true);

        choiceBoxPositionGeneral.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textFieldSalaryGeneral.setDisable(false);
                textFieldWorkingHoursGeneral.setDisable(false);
            }
        });

    }

    public void buttonAddDisable() {
        boolean isAnyFieldEmpty =
                textFieldUsernameGeneral.getText().isEmpty() ||
                        textFieldPasswordGeneral.getText().isEmpty() ||
                        textFieldNameGeneral.getText().isEmpty() ||
                        !(radioButtonFemaleGeneral.isSelected() || radioButtonMaleGeneral.isSelected()) ||
                        textFieldEmailGeneral.getText().isEmpty() || (textFieldPhoneNumberGeneral.getText().length() < 10) ||
                        textFieldPhoneNumberGeneral.getText().isEmpty() || !textFieldEmailGeneral.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") ||
                        textFieldAddressGeneral.getText().isEmpty() || datePickerBirthDateGeneral.getValue().isAfter(datePickerCreationDateGeneral.getValue().minusYears(18)) ||
                        datePickerBirthDateGeneral.getValue() == null ||
                        datePickerCreationDateGeneral.getValue() == null;

                        if(!(choiceBoxPositionGeneral.getValue() == null)){
                            isAnyFieldEmpty |= textFieldSalaryGeneral.getText().isEmpty() ||
                                    textFieldWorkingHoursGeneral.getText().isEmpty();
                        }



        buttonAdd.setDisable(isAnyFieldEmpty);
    }

    public void setButtonAddDisable() {
        buttonAdd.setDisable(true);
        ChangeListener<String> listener = (observable, oldValue, newValue) -> buttonAddDisable();

        textFieldUsernameGeneral.textProperty().addListener(listener);
        textFieldPasswordGeneral.textProperty().addListener(listener);
        textFieldNameGeneral.textProperty().addListener(listener);
        gender.selectedToggleProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldEmailGeneral.textProperty().addListener(listener);
        textFieldPhoneNumberGeneral.textProperty().addListener(listener);
        textFieldAddressGeneral.textProperty().addListener(listener);
        textFieldSalaryGeneral.textProperty().addListener(listener);
        textFieldWorkingHoursGeneral.textProperty().addListener(listener);

        // Với DatePicker và ChoiceBox, dùng valueProperty()
        datePickerBirthDateGeneral.valueProperty().addListener((obs, oldDate, newDate) -> buttonAddDisable());
        datePickerCreationDateGeneral.valueProperty().addListener((obs, oldDate, newDate) -> buttonAddDisable());
        choiceBoxPositionGeneral.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
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

        jumpOnEnter(textFieldUsernameGeneral, textFieldPasswordGeneral);
        jumpOnEnter(textFieldPasswordGeneral, textFieldNameGeneral);
        jumpOnEnter(textFieldNameGeneral, textFieldEmailGeneral);
        jumpOnEnter(textFieldEmailGeneral, textFieldPhoneNumberGeneral);
        jumpOnEnter(textFieldPhoneNumberGeneral, textFieldAddressGeneral);
        jumpOnEnter(textFieldAddressGeneral,datePickerBirthDateGeneral);
        jumpOnEnter(datePickerBirthDateGeneral,datePickerCreationDateGeneral);
        jumpOnEnter(datePickerCreationDateGeneral,choiceBoxPositionGeneral);
        jumpOnEnter(choiceBoxPositionGeneral,textFieldSalaryGeneral);
        jumpOnEnter(textFieldSalaryGeneral,textFieldWorkingHoursGeneral);

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        datePickerCreationDateGeneral.setValue(LocalDate.now());

        exceptions();

        choiceBoxPositionGeneral.getItems().addAll(EEmployeePosition.values());

        setButtonAddDisable();

        buttonEnter();
    }
}
