package com.utc2.petShop.controllers;

import com.utc2.petShop.services.scenes;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class controllerInsertUser implements Initializable {

    @FXML
    private DatePicker BirthDate;

    @FXML
    private VBox VBoxPositionSalary;

    @FXML
    private Button buttonInsert;

    @FXML
    private RadioButton radio2;

    @FXML
    private RadioButton radio3;

    @FXML
    private StackPane stack1;

    @FXML
    private StackPane stackAnnouncement;

    @FXML
    private StackPane stackPaneMain;

    @FXML
    private TextField textAddress;

    @FXML
    private TextField textDepartment;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textFirstName;

    @FXML
    private TextField textLastName;

    @FXML
    private TextField textPhoneNumber;

    @FXML
    private TextField textPosition;

    @FXML
    private TextField textSalary;

    @FXML
    private ToggleGroup user;

    @FXML
    private Label labelEmailAnnouncement;

    @FXML
    private Label labelPhoneNumberAnnouncement;

    @FXML
    private Label labelBirthAnnouncement;

    String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    LocalDate DateNow = LocalDate.now();

    int age = 0;

    @FXML
    void actionInsert(ActionEvent event) throws IOException {
        String FirstName = textFirstName.getText().trim();
        String LastName = textLastName.getText().trim();
        LocalDate Date = BirthDate.getValue();
        age = Period.between(Date, DateNow).getYears();
        String PhoneNumber = textPhoneNumber.getText().trim();
        String Email = textEmail.getText().trim();
        String Address = textAddress.getText().trim();
        String Department = textDepartment.getText().trim();
        String Salary = textSalary.getText().trim();
        String Position = textPosition.getText().trim();
        if (!FirstName.isEmpty() && !LastName.isEmpty() && Date != null && !PhoneNumber.isEmpty() && !Email.isEmpty() && !Address.isEmpty()) {
            if (PhoneNumber.length() != 10 || !PhoneNumber.matches("\\d{10}")) {
                labelPhoneNumberAnnouncement.setVisible(true);
                return;
            } else {
                labelPhoneNumberAnnouncement.setVisible(false);
            }
            if(age < 18){
                labelBirthAnnouncement.setVisible(true);
            }else {
                labelBirthAnnouncement.setVisible(false);
            }
            if (radio2.isSelected() && !Department.isEmpty()) {
                scenes.switchScene("sampleProgressBarAdmin","Golden Pet Shop", "controllerProgressBarAdmin",false);

                    //lấy dữ liệu của manager ở đây

            }
            else if (radio3.isSelected() && !Position.isEmpty() && !Salary.isEmpty()) {
                scenes.switchScene("sampleProgressBarEmployee","Golden Pet Shop", "controllerProgressBarEmployee",false);

                //lấy dữ liệu employee ở đây

            }
            else {
                stackAnnouncement.setVisible(true);
            }
        }
        else {
            stackAnnouncement.setVisible(true);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelBirthAnnouncement.setVisible(false);
        labelPhoneNumberAnnouncement.setVisible(false);
        labelEmailAnnouncement.setVisible(false);
        stackAnnouncement.setVisible(false);
        VBoxPositionSalary.setVisible(false);
        VBoxPositionSalary.setManaged(false);
        textDepartment.setVisible(false);
        textPosition.setVisible(false);
        textSalary.setVisible(false);
        textDepartment.setManaged(false);
        textPosition.setManaged(false);
        textSalary.setManaged(false);
        radio2.setOnAction(event -> {
            if (radio2.isSelected()) {
                textPosition.setVisible(false);
                textSalary.setVisible(false);
                textPosition.setManaged(false);
                textSalary.setManaged(false);
                textDepartment.setManaged(true);
                textDepartment.setVisible(true);
                VBoxPositionSalary.setVisible(false);
                VBoxPositionSalary.setManaged(false);
            }
        });
        radio3.setOnAction(event -> {
            if (radio3.isSelected()) {
                textDepartment.setVisible(false);
                textDepartment.setManaged(false);
                textPosition.setManaged(true);
                textPosition.setVisible(true);
                textSalary.setManaged(true);
                textSalary.setVisible(true);
                VBoxPositionSalary.setVisible(true);
                VBoxPositionSalary.setManaged(true);
            }
        });

        BirthDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                age = Period.between(newValue, DateNow).getYears();
                labelBirthAnnouncement.setVisible(age < 18);
            }
        });
        // Định dạng ngày muốn hiển thị (dd/MM/yyyy)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        BirthDate.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? formatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String text) {
                try {
                    return (text != null && !text.isEmpty()) ? LocalDate.parse(text, formatter) : null;
                } catch (Exception e) {
                    return null; // Nếu lỗi, trả về null để tránh crash
                }
            }
        });

        Pattern pattern = Pattern.compile("\\d*"); // Chỉ cho phép số
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (pattern.matcher(change.getControlNewText()).matches()) {
                return change; // Hợp lệ -> chấp nhận thay đổi
            } else {
                return null; // Không hợp lệ -> bỏ qua
            }
        };
        textPhoneNumber.setTextFormatter(new TextFormatter<>(filter));

        textEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            String email = textEmail.getText().trim();
            if (!email.matches(emailRegex)) {
                labelEmailAnnouncement.setVisible(true);  // Hiển thị thông báo lỗi
            } else {
                labelEmailAnnouncement.setVisible(false); // Ẩn thông báo lỗi
            }
        });

        Platform.runLater(() ->textFirstName.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                textLastName.requestFocus();
            }
        }));
        textLastName.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                BirthDate.requestFocus();
            }
        });
        BirthDate.setOnKeyReleased(event -> {
           if (event.getCode() == KeyCode.ENTER) {
               textPhoneNumber.requestFocus();
           }
        });
        textPhoneNumber.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                textEmail.requestFocus();
            }
        });
        textEmail.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                textAddress.requestFocus();
            }
        });
        radio2.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                textDepartment.requestFocus();
            }
        });
        textDepartment.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonInsert.fire();
            }
        });
        radio3.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                textPosition.requestFocus();
            }
        });
        textPosition.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                textSalary.requestFocus();
            }
        });
        textSalary.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonInsert.fire();
            }
        });
    }
}
