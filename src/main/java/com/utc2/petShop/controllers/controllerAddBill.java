package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Bill.Bill;
import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.entities.User.Employee;
import com.utc2.petShop.model.entities.User.User;
import com.utc2.petShop.model.repository.Insert.InsertBill;
import com.utc2.petShop.model.repository.Select.SelectCustomer;
import com.utc2.petShop.model.repository.Select.SelectUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class controllerAddBill implements Initializable {

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

        InsertBill.insertBill(employee, customer, invoiceDate, totalAmount, paymentMethod, status);
    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage) buttonCancel.getScene().getWindow()).close();
    }

    public void exceptions() {
        TextFormatter<String> formatterTotalAmount = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.matches("\\d*(\\.\\d{0,2})?")) {
                return change;
            } else {
                return null;
            }
        });
        textFieldTotalAmountGeneral.setTextFormatter(formatterTotalAmount);
    }

    private static ObservableList<Employee> employees = FXCollections.observableArrayList(SelectUser.getAllEmployees());

    private static ObservableList<Customer> customers = FXCollections.observableArrayList(SelectCustomer.getAllCustomers());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exceptions();

        comboBoxEmployeeGeneral.setItems(employees);

        comboBoxCustomerGeneral.setItems(customers);

        choiceBoxPaymentMethodGeneral.getItems().addAll("cash","Banking");
    }
}
