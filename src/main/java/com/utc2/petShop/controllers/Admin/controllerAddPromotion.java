package com.utc2.petShop.controllers.Admin;

import com.utc2.petShop.model.repository.Insert.InsertPromotion;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class controllerAddPromotion implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancel;

    @FXML
    private CheckBox checkBoxActiveGeneral;

    @FXML
    private DatePicker datePickerEndDateGeneral;

    @FXML
    private DatePicker datePickerStartDateGeneral;

    @FXML
    private GridPane gridPaneGeneral;

    @FXML
    private TextField textFieldDiscountPercentGeneral;

    @FXML
    private TextField textFieldNameGeneral;

    @FXML
    void actionAdd(ActionEvent event) {
        String name = textFieldNameGeneral.getText();
        float discountPercentage = Float.parseFloat(textFieldDiscountPercentGeneral.getText());
        LocalDate startDate = datePickerStartDateGeneral.getValue();
        LocalDate endDate = datePickerEndDateGeneral.getValue();
        Boolean isActive = checkBoxActiveGeneral.isSelected();

        InsertPromotion.insertPromotion(name, discountPercentage, startDate, endDate, isActive);

        ((Stage) buttonCancel.getScene().getWindow()).close();

    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage) buttonCancel.getScene().getWindow()).close();
    }

    public void exceptions() {
        TextFormatter<String> formatterDiscount = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.matches("\\d{0,2}(\\.\\d{0,2})?")) {
                return change;
            } else {
                return null;
            }
        });
        textFieldDiscountPercentGeneral.setTextFormatter(formatterDiscount);

        datePickerStartDateGeneral.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (!empty && date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // màu hồng nhạt cho ngày bị vô hiệu hóa
                }
            }
        });
        datePickerEndDateGeneral.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (!empty && date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // màu hồng nhạt cho ngày bị vô hiệu hóa
                }
            }
        });
    }

    public void buttonAddDisable() {
        boolean isAnyFieldEmpty = textFieldNameGeneral.getText().isEmpty()
                || textFieldDiscountPercentGeneral.getText().isEmpty()
                || datePickerStartDateGeneral.getValue() == null
                || datePickerEndDateGeneral.getValue() == null;

        // Sau đó xử lý ví dụ như vô hiệu hóa nút:
        buttonAdd.setDisable(isAnyFieldEmpty);
    }

    public void setButtonAddDisable() {
        buttonAdd.setDisable(true);
        textFieldNameGeneral.textProperty().addListener((observable, oldValue, newValue) -> buttonAddDisable());
        textFieldDiscountPercentGeneral.textProperty().addListener((observable, oldValue, newValue) -> buttonAddDisable());
        datePickerStartDateGeneral.valueProperty().addListener((observable, oldValue, newValue) -> buttonAddDisable());
        datePickerEndDateGeneral.valueProperty().addListener((observable, oldValue, newValue) -> buttonAddDisable());
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

        jumpOnEnter(textFieldNameGeneral, textFieldDiscountPercentGeneral);
        jumpOnEnter(textFieldDiscountPercentGeneral,datePickerStartDateGeneral);
        jumpOnEnter(datePickerStartDateGeneral,datePickerEndDateGeneral);

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

        exceptions();

        setButtonAddDisable();

        buttonEnter();
    }
}
