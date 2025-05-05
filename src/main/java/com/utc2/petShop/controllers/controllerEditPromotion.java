package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Promotion.Promotion;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.repository.UpdateById.UpdatePromotion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class controllerEditPromotion implements Initializable {

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

        UpdatePromotion.updatePromotion(promotion.getId(), name, discountPercentage, startDate, endDate, isActive);

        ((Stage) buttonCancel.getScene().getWindow()).close();

    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage)buttonCancel.getScene().getWindow()).close();
    }

    public void exceptions(){
        TextFormatter<String> formatterDiscount = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.matches("\\d{0,3}(\\.\\d{0,2})?")) {
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

    public void receiveData(Promotion obj){
        promotion = obj;
        textFieldNameGeneral.setText(obj.getName());
        textFieldDiscountPercentGeneral.setText(String.valueOf(obj.getDiscountPercent()));
        datePickerStartDateGeneral.setValue(obj.getStartDate());
        datePickerEndDateGeneral.setValue(obj.getEndDate());
        checkBoxActiveGeneral.setSelected(obj.isIsActive());
    }

    Promotion promotion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exceptions();
    }
}
