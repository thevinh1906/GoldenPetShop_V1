package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Promotion.Promotion;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
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
    }

    public void receiveData(Promotion obj){
        textFieldNameGeneral.setText(obj.getName());
        textFieldDiscountPercentGeneral.setText(String.valueOf(obj.getDiscountPercent()));
        datePickerStartDateGeneral.setValue(obj.getStartDate());
        datePickerEndDateGeneral.setValue(obj.getEndDate());
        checkBoxActiveGeneral.setSelected(obj.isIsActive());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
