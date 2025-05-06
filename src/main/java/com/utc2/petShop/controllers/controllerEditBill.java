package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Bill.Bill;
import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.entities.User.Employee;
import com.utc2.petShop.model.repository.Insert.InsertBill;
import com.utc2.petShop.model.repository.Select.SelectCustomer;
import com.utc2.petShop.model.repository.Select.SelectUser;
import com.utc2.petShop.model.repository.UpdateById.UpdateBill;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class controllerEditBill implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancel;

    @FXML
    private CheckBox checkBoxStatusGeneral;

    @FXML
    private ChoiceBox<String> choiceBoxPaymentMethodGeneral;

    @FXML
    private ComboBox<Customer> comboBoxCustomerGeneral;

    @FXML
    private ComboBox<Employee> comboBoxEmployeeGeneral;

    @FXML
    private DatePicker datePickerInvoiceDateGeneral;

    @FXML
    private GridPane gridPaneGeneral;

    @FXML
    private TextField textFieldTotalAmountGeneral;

    @FXML
    void actionAdd(ActionEvent event) {
        Employee employee = comboBoxEmployeeGeneral.getValue();
        Customer customer = comboBoxCustomerGeneral.getValue();
        LocalDate invoiceDate = datePickerInvoiceDateGeneral.getValue();
        double totalAmount = Double.parseDouble(textFieldTotalAmountGeneral.getText());
        String paymentMethod = choiceBoxPaymentMethodGeneral.getValue();
        String status = checkBoxStatusGeneral.isSelected() ? "completed" : "pending";

        UpdateBill.updateBill(bill.getId(),employee,customer, invoiceDate, totalAmount, paymentMethod, status);

        ((Stage) buttonCancel.getScene().getWindow()).close();

    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage) buttonCancel.getScene().getWindow()).close();
    }

    public void exceptions() {
        TextFormatter<String> formatterTotalAmount = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.matches("\\d{0,10}(\\.\\d{0,2})?")) {
                return change;
            } else {
                return null;
            }
        });
        textFieldTotalAmountGeneral.setTextFormatter(formatterTotalAmount);

        datePickerInvoiceDateGeneral.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (!empty && date.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // đỏ nhạt cho ngày không hợp lệ
                }
            }
        });
    }

    public void buttonAddDisable() {
        boolean isAnyFieldEmpty =
                comboBoxEmployeeGeneral.getValue() == null ||
                        comboBoxCustomerGeneral.getValue() == null ||
                        datePickerInvoiceDateGeneral.getValue() == null ||
                        textFieldTotalAmountGeneral.getText().isEmpty() ||
                        choiceBoxPaymentMethodGeneral.getValue() == null;

        buttonAdd.setDisable(isAnyFieldEmpty);
    }

    public void setButtonAddDisable() {
        buttonAdd.setDisable(true);
        textFieldTotalAmountGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        comboBoxEmployeeGeneral.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        comboBoxCustomerGeneral.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        datePickerInvoiceDateGeneral.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        choiceBoxPaymentMethodGeneral.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
    }

    public void receiveData(Bill obj){
        bill = obj;
        comboBoxCustomerGeneral.setValue(obj.getCustomer());
        comboBoxEmployeeGeneral.setValue(obj.getEmployee());
        datePickerInvoiceDateGeneral.setValue(obj.getInvoiceDate());
        textFieldTotalAmountGeneral.setText(String.valueOf(obj.getTotalAmount()));
        choiceBoxPaymentMethodGeneral.setValue(obj.getPaymentMethod());
        if(obj.getStatus().equals("pending")){
            checkBoxStatusGeneral.setSelected(false);
        }
        else if(obj.getStatus().equals("completed")){
            checkBoxStatusGeneral.setSelected(true);
        }
    }

    private static ObservableList<Employee> employees = FXCollections.observableArrayList(SelectUser.getAllEmployees());

    private static ObservableList<Customer> customers = FXCollections.observableArrayList(SelectCustomer.getAllCustomers());

    private static Bill bill;

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
        jumpOnEnter(comboBoxCustomerGeneral, comboBoxEmployeeGeneral);
        jumpOnEnter(comboBoxEmployeeGeneral,datePickerInvoiceDateGeneral);
        jumpOnEnter(datePickerInvoiceDateGeneral,textFieldTotalAmountGeneral);
        jumpOnEnter(textFieldTotalAmountGeneral,choiceBoxPaymentMethodGeneral);


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

        comboBoxEmployeeGeneral.setItems(employees);

        comboBoxCustomerGeneral.setItems(customers);

        choiceBoxPaymentMethodGeneral.getItems().addAll("Cash","Banking");

        setButtonAddDisable();

        buttonEnter();
    }
}
