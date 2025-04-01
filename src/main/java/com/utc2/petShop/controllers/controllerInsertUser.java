package com.utc2.petShop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class controllerInsertUser implements Initializable {

    @FXML
    private RadioButton radio1;

    @FXML
    private RadioButton radio2;

    @FXML
    private RadioButton radio3;

    @FXML
    private TextField textDepartment;

    @FXML
    private TextField textPosition;

    @FXML
    private TextField textSalary;

    @FXML
    private ToggleGroup user;

    @FXML
    private StackPane stack1;

    @FXML
    private DatePicker BirthDate;

    @FXML
    private VBox VBoxPositionSalary;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBoxPositionSalary.setVisible(false);
        VBoxPositionSalary.setManaged(false);
        textDepartment.setVisible(false);
        textPosition.setVisible(false);
        textSalary.setVisible(false);
        textDepartment.setManaged(false);
        textPosition.setManaged(false);
        textSalary.setManaged(false);
        radio1.setOnAction(event -> {
            if (radio1.isSelected()) {
                textDepartment.setVisible(false);
                textDepartment.setManaged(false);
                textPosition.setVisible(false);
                textSalary.setVisible(false);
                textPosition.setManaged(false);
                textSalary.setManaged(false);
                VBoxPositionSalary.setVisible(false);
                VBoxPositionSalary.setManaged(false);
            }
        });
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
        // Định dạng ngày muốn hiển thị (dd/MM/yyyy)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Thiết lập bộ chuyển đổi định dạng cho DatePicker
        BirthDate.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? formatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String text) {
                return (text != null && !text.isEmpty()) ? LocalDate.parse(text, formatter) : null;
            }
        });
    }
}
