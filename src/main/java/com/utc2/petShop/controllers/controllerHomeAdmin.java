package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Bill.Bill;
import com.utc2.petShop.model.entities.Customer.Customer;
import com.utc2.petShop.model.entities.Pet.*;
import com.utc2.petShop.model.entities.Product.*;
import com.utc2.petShop.model.entities.Promotion.Promotion;
import com.utc2.petShop.model.entities.RevenueReport.RevenueReport;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.entities.User.Employee;
import com.utc2.petShop.model.entities.User.User;
import com.utc2.petShop.model.services.scenes;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.DoubleBinaryOperator;

public class controllerHomeAdmin implements Initializable {


    @FXML
    private BorderPane borderPaneBill;

    @FXML
    private BorderPane borderPaneCustomer;

    @FXML
    private BorderPane borderPanePet;

    @FXML
    private BorderPane borderPaneProduct;

    @FXML
    private BorderPane borderPanePromotion;

    @FXML
    private BorderPane borderPaneRevenueReport;

    @FXML
    private BorderPane borderPaneSupplier;

    @FXML
    private BorderPane borderPaneUser;

    @FXML
    private Button buttonAccount;

    @FXML
    private Button buttonAddBill;

    @FXML
    private Button buttonAddCustomer;

    @FXML
    private Button buttonAddPet;

    @FXML
    private Button buttonAddProduct;

    @FXML
    private Button buttonAddPromotion;

    @FXML
    private Button buttonAddRevenueReport;

    @FXML
    private Button buttonAddSupplier;

    @FXML
    private Button buttonAddUser;

    @FXML
    private Button buttonArrangeBill;

    @FXML
    private Button buttonArrangeCustomer;

    @FXML
    private Button buttonArrangePet;

    @FXML
    private Button buttonArrangeProduct;

    @FXML
    private Button buttonArrangePromotion;

    @FXML
    private Button buttonArrangeRevenueReport;

    @FXML
    private Button buttonArrangeUser;

    @FXML
    private Button buttonBill;

    @FXML
    private Button buttonCustomer;

    @FXML
    private Button buttonDeleteBill;

    @FXML
    private Button buttonDeleteCustomer;

    @FXML
    private Button buttonDeletePet;

    @FXML
    private Button buttonDeleteProduct;

    @FXML
    private Button buttonDeletePromotion;

    @FXML
    private Button buttonDeleteRevenueReport;

    @FXML
    private Button buttonDeleteSupplier;

    @FXML
    private Button buttonDeleteUser;

    @FXML
    private Button buttonDetailBill;

    @FXML
    private Button buttonDetailPet;

    @FXML
    private Button buttonDetailProduct;

    @FXML
    private Button buttonDetailPromotion;

    @FXML
    private Button buttonDetailRevenueReport;

    @FXML
    private Button buttonDetailUser;

    @FXML
    private Button buttonEditBill;

    @FXML
    private Button buttonEditCustomer;

    @FXML
    private Button buttonEditPet;

    @FXML
    private Button buttonEditProduct;

    @FXML
    private Button buttonEditPromotion;

    @FXML
    private Button buttonEditRevenueReport;

    @FXML
    private Button buttonEditSupplier;

    @FXML
    private Button buttonEditUser;

    @FXML
    private Button buttonExcelBill;

    @FXML
    private Button buttonExcelCustomer;

    @FXML
    private Button buttonExcelPet;

    @FXML
    private Button buttonExcelProduct;

    @FXML
    private Button buttonExcelPromotion;

    @FXML
    private Button buttonExcelRevenueReport;

    @FXML
    private Button buttonExcelSupplier;

    @FXML
    private Button buttonExcelUser;

    @FXML
    private Button buttonFilterBill;

    @FXML
    private Button buttonFilterCustomer;

    @FXML
    private Button buttonFilterPet;

    @FXML
    private Button buttonFilterProduct;

    @FXML
    private Button buttonFilterPromotion;

    @FXML
    private Button buttonFilterRevenueReport;

    @FXML
    private Button buttonFilterUser;

    @FXML
    private Button buttonFind;

    @FXML
    private Button buttonImportProduct;

    @FXML
    private Button buttonImportProductImportProduct;

    @FXML
    private Button buttonLeftAddImportProduct;

    @FXML
    private Button buttonPet;

    @FXML
    private Button buttonProduct;

    @FXML
    private Button buttonPromotion;

    @FXML
    private Button buttonRevenueReport;

    @FXML
    private Button buttonRightDeleteImportProduct;

    @FXML
    private Button buttonRightEditImportProduct;

    @FXML
    private Button buttonRightExcelImportProduct;

    @FXML
    private Button buttonSetting;

    @FXML
    private Button buttonSupplier;

    @FXML
    private Button buttonUser;

    @FXML
    private GridPane gridPaneImportProduct;

    @FXML
    private Label labelTotalCostImportProduct;

    @FXML
    private ImageView logoImg;

    @FXML
    private ScrollPane scrollPaneBill;

    @FXML
    private ScrollPane scrollPaneCenter;

    @FXML
    private ScrollPane scrollPaneCustomer;

    @FXML
    private ScrollPane scrollPaneLeftImportProduct;

    @FXML
    private ScrollPane scrollPanePet;

    @FXML
    private ScrollPane scrollPaneProduct;

    @FXML
    private ScrollPane scrollPanePromotion;

    @FXML
    private ScrollPane scrollPaneRevenueReport;

    @FXML
    private ScrollPane scrollPaneRightImportProduct;

    @FXML
    private ScrollPane scrollPaneSupplier;

    @FXML
    private ScrollPane scrollPaneUser;

    @FXML
    private ScrollPane srcollPaneLeft;

    @FXML
    private StackPane stackPaneBill;

    @FXML
    private StackPane stackPaneCustomer;

    @FXML
    private StackPane stackPaneImportProduct;

    @FXML
    private StackPane stackPanePet;

    @FXML
    private StackPane stackPaneProduct;

    @FXML
    private StackPane stackPanePromotion;

    @FXML
    private StackPane stackPaneRevenueReport;

    @FXML
    private StackPane stackPaneSupplier;

    @FXML
    private StackPane stackPaneUser;

    @FXML
    private TableColumn<Supplier, String> tableColumnAddressSupplier;

    @FXML
    private TableColumn<User, String> tableColumnAddressUser;

    @FXML
    private TableColumn<Pet, Integer> tableColumnAgePet;

    @FXML
    private TableColumn<User, String> tableColumnBirthDateUser;

    @FXML
    private TableColumn<Product, String> tableColumnBrandProduct;

    @FXML
    private TableColumn<Pet, String> tableColumnBreedPet;

    @FXML
    private TableColumn<User, String> tableColumnCreateAtUser;

    @FXML
    private TableColumn<Bill, String> tableColumnCustomerBill;

    @FXML
    private TableColumn<Product, String> tableColumnCategoriesProduct;

    @FXML
    private TableColumn<Bill, String> tableColumnDateBill;

    @FXML
    private TableColumn<Product, String> tableColumnDimensionProduct;

    @FXML
    private TableColumn<Promotion, Double> tableColumnDiscountPercentPromotion;

    @FXML
    private TableColumn<Promotion, Double> tableColumnDiscountPercentagePromotion;

    @FXML
    private TableColumn<Pet, String> tableColumnEarLengthPet;

    @FXML
    private TableColumn<Supplier, String> tableColumnEmailSupplier;

    @FXML
    private TableColumn<User, String> tableColumnEmailUser;

    @FXML
    private TableColumn<Bill, String> tableColumnEmployeeBill;

    @FXML
    private TableColumn<Promotion, String> tableColumnEndDatePromotion;

    @FXML
    private TableColumn<Product, String> tableColumnExpirationDateProduct;

    @FXML
    private TableColumn<Pet, String> tableColumnEyeColorPet;

    @FXML
    private TableColumn<Product, String> tableColumnFlavorProduct;

    @FXML
    private TableColumn<Pet, String> tableColumnFurColorPet;

    @FXML
    private TableColumn<Pet, String> tableColumnGenderPet;

    @FXML
    private TableColumn<User, String> tableColumnGenderUser;

    @FXML
    private TableColumn<Pet, String> tableColumnHealthStatusPet;

    @FXML
    private TableColumn<Bill, String> tableColumnIDBill;

    @FXML
    private TableColumn<Customer, String> tableColumnIDCustomer;

    @FXML
    private TableColumn<Pet, String> tableColumnIDPet;

    @FXML
    private TableColumn<Product, String> tableColumnIDProduct;

    @FXML
    private TableColumn<Promotion, String> tableColumnIDPromotion;

    @FXML
    private TableColumn<RevenueReport, String> tableColumnIDRevenueReport;

    @FXML
    private TableColumn<Supplier, String> tableColumnIDSupplier;

    @FXML
    private TableColumn<User, String> tableColumnIDUser;

    @FXML
    private TableColumn<Promotion, String> tableColumnIsActivePromotion;

    @FXML
    private TableColumn<Pet, String> tableColumnIsIndoorPet;

    @FXML
    private TableColumn<Pet, String> tableColumnIsTrainedPet;

    @FXML
    private TableColumn<Product, String> tableColumnLeftIDImportProduct;

    @FXML
    private TableColumn<Product, String> tableColumnLeftNameImportProduct;

    @FXML
    private TableColumn<Product, Double> tableColumnLeftPriceImportProduct;

    @FXML
    private TableColumn<Product, Integer> tableColumnLeftQuantityImportProduct;

    @FXML
    private TableColumn<Product, String> tableColumnManufacturerProduct;

    @FXML
    private TableColumn<Product, String> tableColumnMaterialProduct;

    @FXML
    private TableColumn<RevenueReport, Integer> tableColumnMonthRevenueReport;

    @FXML
    private TableColumn<Customer, String> tableColumnNameCustomer;

    @FXML
    private TableColumn<Pet, String> tableColumnNamePet;

    @FXML
    private TableColumn<Product, String> tableColumnNameProduct;

    @FXML
    private TableColumn<Promotion, String> tableColumnNamePromotion;

    @FXML
    private TableColumn<Supplier, String> tableColumnNameSupplier;

    @FXML
    private TableColumn<User, String> tableColumnNameUser;

    @FXML
    private TableColumn<Product, String> tableColumnNoteProduct;

    @FXML
    private TableColumn<Promotion, String> tableColumnNotePromotion;

    @FXML
    private TableColumn<RevenueReport, String> tableColumnNoteRevenueReport;

    @FXML
    private TableColumn<User, String> tableColumnNoteUser;

    @FXML
    private TableColumn<Pet, String> tableColumnNotePet;

    @FXML
    private TableColumn<Pet, String> tableColumnOriginPet;

    @FXML
    private TableColumn<User, String> tableColumnPasswordUser;

    @FXML
    private TableColumn<Bill, String> tableColumnPaymentMethodBill;

    @FXML
    private TableColumn<Customer, String> tableColumnPhoneNumberCustomer;

    @FXML
    private TableColumn<Supplier, String> tableColumnPhoneNumberSupplier;

    @FXML
    private TableColumn<User, String> tableColumnPositionUser;

    @FXML
    private TableColumn<Pet, Double> tableColumnPricePet;

    @FXML
    private TableColumn<Product, Double> tableColumnPriceProduct;

    @FXML
    private TableColumn<Product, Integer> tableColumnQuantityProduct;

    @FXML
    private TableColumn<Product, String> tableColumnRightIDImportProduct;

    @FXML
    private TableColumn<Product, String> tableColumnRightNameImportProduct;

    @FXML
    private TableColumn<Product, Void> tableColumnRightNumericalOrderImportProduct;

    @FXML
    private TableColumn<Product, Double> tableColumnRightPriceImportProduct;

    @FXML
    private TableColumn<Product, Integer> tableColumnRightQuantityImportProduct;

    @FXML
    private TableColumn<Product, Double> tableColumnRightTotalPriceImportProduct;

    @FXML
    private TableColumn<User, Double> tableColumnSalaryUser;

    @FXML
    private TableColumn<Product, String> tableColumnSizeProduct;

    @FXML
    private TableColumn<Promotion, String> tableColumnStartDatePromotion;

    @FXML
    private TableColumn<Bill, String> tableColumnStatusBill;

    @FXML
    private TableColumn<Pet, String> tableColumnSupplierPet;

    @FXML
    private TableColumn<Product, String> tableColumnSupplierProduct;

    @FXML
    private TableColumn<Pet, String> tableColumnTailLengthPet;

    @FXML
    private TableColumn<Bill, Double> tableColumnTotalAmountBill;

    @FXML
    private TableColumn<RevenueReport, Integer> tableColumnTotalBillRevenueReport;

    @FXML
    private TableColumn<RevenueReport, Double> tableColumnTotalRevenueRevenueReport;

    @FXML
    private TableColumn<Product, String> tableColumnTypeProduct;

    @FXML
    private TableColumn<User, String> tableColumnUsernameUser;

    @FXML
    private TableColumn<Pet, Boolean> tableColumnVaccinatedPet;

    @FXML
    private TableColumn<Pet, Double> tableColumnWeightPet;

    @FXML
    private TableColumn<User, String> tableColumnWorkingHoursUser;

    @FXML
    private TableColumn<RevenueReport, Integer> tableColumnYearRevenueReport;

    @FXML
    private TableView<Bill> tableViewBill;

    @FXML
    private TableView<Customer> tableViewCustomer;

    @FXML
    private TableView<Product> tableViewLeftImportProduct;

    @FXML
    private TableView<Pet> tableViewPet;

    @FXML
    private TableView<Product> tableViewProduct;

    @FXML
    private TableView<Promotion> tableViewPromotion;

    @FXML
    private TableView<RevenueReport> tableViewRevenueReport;

    @FXML
    private TableView<Product> tableViewRightImportProduct;

    @FXML
    private TableView<Supplier> tableViewSupplier;

    @FXML
    private TableView<User> tableViewUser;

    @FXML
    private TextField textFieldLeftQuantityImportProduct;

    @FXML
    private TextField textSreach;

    @FXML
    private VBox vBoxCenterHomeAdmin;

    @FXML
    private VBox vBoxLeftImportProduct;

    @FXML
    private VBox vBoxRightImportProduct;

    @FXML
    void actionAccount(ActionEvent event) {

    }

    @FXML
    void actionAddBill(ActionEvent event) {

    }

    @FXML
    void actionAddCustomer(ActionEvent event) {

    }

    @FXML
    void actionAddPet(ActionEvent event) throws IOException {
        scenes.openMoreScene("sampleAddPet", "Golden Pet Shop", "applicationAddPet",false);
    }

    @FXML
    void actionAddProduct(ActionEvent event) {

    }

    @FXML
    void actionAddPromotion(ActionEvent event) {

    }

    @FXML
    void actionAddRevenueReport(ActionEvent event) {

    }

    @FXML
    void actionAddSupplier(ActionEvent event) {

    }

    @FXML
    void actionAddUser(ActionEvent event) {

    }

    @FXML
    void actionArrangeCustomer(ActionEvent event) {

    }

    @FXML
    void actionArrangePet(ActionEvent event) {

    }

    @FXML
    void actionArrangePetBill(ActionEvent event) {

    }

    @FXML
    void actionArrangePetRevenueReport(ActionEvent event) {

    }

    @FXML
    void actionArrangeProduct(ActionEvent event) {

    }

    @FXML
    void actionArrangeUser(ActionEvent event) {

    }

    @FXML
    void actionBack(ActionEvent event) {

    }

    @FXML
    void actionBill(ActionEvent event) {
        hideScreen();

        stackPaneBill.setVisible(true);
        stackPaneBill.setManaged(true);
    }

    @FXML
    void actionCustomer(ActionEvent event) {
        hideScreen();

        stackPaneCustomer.setManaged(true);
        stackPaneCustomer.setVisible(true);
    }

    @FXML
    void actionDeleteBill(ActionEvent event) {

    }

    @FXML
    void actionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void actionDeletePet(ActionEvent event) {

    }

    @FXML
    void actionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void actionDeleteRevenueReport(ActionEvent event) {

    }

    @FXML
    void actionDeleteSupplier(ActionEvent event) {

    }

    @FXML
    void actionDeleteUser(ActionEvent event) {

    }

    @FXML
    void actionDetailBill(ActionEvent event) {

    }

    @FXML
    void actionDetailPet(ActionEvent event) {

    }

    @FXML
    void actionDetailProduct(ActionEvent event) {

    }

    @FXML
    void actionDetailRevenueReport(ActionEvent event) {

    }

    @FXML
    void actionDetailUser(ActionEvent event) {

    }

    @FXML
    void actionEditBill(ActionEvent event) {

    }

    @FXML
    void actionEditCustomer(ActionEvent event) {

    }

    @FXML
    void actionEditPet(ActionEvent event) {

    }

    @FXML
    void actionEditProduct(ActionEvent event) {

    }

    @FXML
    void actionEditRevenueReport(ActionEvent event) {

    }

    @FXML
    void actionEditSupplier(ActionEvent event) {

    }

    @FXML
    void actionEditUser(ActionEvent event) {

    }

    @FXML
    void actionExcelBill(ActionEvent event) {

    }

    @FXML
    void actionExcelCustomer(ActionEvent event) {

    }

    @FXML
    void actionExcelPet(ActionEvent event) {

    }

    @FXML
    void actionExcelProduct(ActionEvent event) {

    }

    @FXML
    void actionExcelRevenueReport(ActionEvent event) {

    }

    @FXML
    void actionExcelSupplier(ActionEvent event) {

    }

    @FXML
    void actionExcelUser(ActionEvent event) {

    }

    @FXML
    void actionFilterBill(ActionEvent event) {

    }

    @FXML
    void actionFilterCustomer(ActionEvent event) {

    }

    @FXML
    void actionFilterPet(ActionEvent event) {

    }

    @FXML
    void actionFilterProduct(ActionEvent event) {

    }

    @FXML
    void actionFilterRevenueReport(ActionEvent event) {

    }

    @FXML
    void actionFilterUser(ActionEvent event) {

    }

    @FXML
    void actionFind(ActionEvent event) {

    }

    @FXML
    void actionForward(ActionEvent event) {

    }

    @FXML
    void actionImportProduct(ActionEvent event) {
        hideScreen();

        stackPaneImportProduct.setVisible(true);
        stackPaneImportProduct.setManaged(true);
    }

    @FXML
    void actionImportProductImportProduct(ActionEvent event) {

    }

    @FXML
    void actionLeftAddImportProduct(ActionEvent event) {
//        if (textFieldLeftQuantityImportProduct.getText().equals("")) {
//
//        } else {

        Product selectedLeftProduct = tableViewLeftImportProduct.getSelectionModel().getSelectedItem();


        if (selectedLeftProduct != null) {
            for(Product p : tableViewRightImportProduct.getItems()){
                if(p.getId() == selectedLeftProduct.getId()){
                    p.setQuantity(p.getQuantity() + Integer.parseInt(textFieldLeftQuantityImportProduct.getText()));
                    labelTotalCostImportProduct.setText(String.valueOf(p.getPrice() * p.getQuantity()));
                    return;
                }
            }
            Product product = new Product();
            product.setId(selectedLeftProduct.getId());
            product.setName(selectedLeftProduct.getName());
            product.setPrice(selectedLeftProduct.getPrice());
            product.setQuantity(Integer.parseInt(textFieldLeftQuantityImportProduct.getText()));
            tableViewRightImportProduct.getItems().add(product);

            double totalPrice = 0;
            for (Product p : tableViewRightImportProduct.getItems()) {
                totalPrice += p.getPrice() * p.getQuantity();
            }

            labelTotalCostImportProduct.setText(String.valueOf(totalPrice));
        }
    }
//    }

    @FXML
    void actionPet(ActionEvent event) {
        hideScreen();

        stackPanePet.setVisible(true);
        stackPanePet.setManaged(true);
    }

    @FXML
    void actionProduct(ActionEvent event) {
        hideScreen();

        stackPaneProduct.setVisible(true);
        stackPaneProduct.setManaged(true);
    }

    @FXML
    void actionPromotion(ActionEvent event) {
        hideScreen();

        stackPanePromotion.setVisible(true);
        stackPanePromotion.setManaged(true);
    }

    @FXML
    void actionRevenueReport(ActionEvent event) {
        hideScreen();

        stackPaneRevenueReport.setVisible(true);
        stackPaneRevenueReport.setManaged(true);
    }

    @FXML
    void actionRightDeleteImportProduct(ActionEvent event) {

    }

    @FXML
    void actionRightEditImportProduct(ActionEvent event) {

    }

    @FXML
    void actionRightExcelImportProduct(ActionEvent event) {

    }

    @FXML
    void actionSetting(ActionEvent event) {

    }

    @FXML
    void actionSupplier(ActionEvent event) {
        hideScreen();

        stackPaneSupplier.setVisible(true);
        stackPaneSupplier.setManaged(true);
    }

    @FXML
    void actionUser(ActionEvent event) {
        hideScreen();

        stackPaneUser.setManaged(true);
        stackPaneUser.setVisible(true);
    }

    public void hideScreen() {
        stackPaneUser.setManaged(false);
        stackPaneUser.setVisible(false);

        stackPaneCustomer.setManaged(false);
        stackPaneCustomer.setVisible(false);

        stackPaneImportProduct.setVisible(false);
        stackPaneImportProduct.setManaged(false);

        stackPanePet.setVisible(false);
        stackPanePet.setManaged(false);

        stackPaneProduct.setVisible(false);
        stackPaneProduct.setManaged(false);

        stackPaneSupplier.setVisible(false);
        stackPaneSupplier.setManaged(false);

        stackPaneBill.setVisible(false);
        stackPaneBill.setManaged(false);

        stackPanePromotion.setVisible(false);
        stackPanePromotion.setManaged(false);

        stackPaneRevenueReport.setVisible(false);
        stackPaneRevenueReport.setManaged(false);
    }

    public void heightAdjustment() {
        scrollPaneCenter.heightProperty().addListener((observable, oldValue, newValue) -> {
            vBoxCenterHomeAdmin.setPrefHeight(newValue.doubleValue());
        });


        vBoxCenterHomeAdmin.heightProperty().addListener((observable, oldValue, newValue) -> {
            stackPanePet.setPrefHeight(newValue.doubleValue());
            stackPaneProduct.setPrefHeight(newValue.doubleValue());
            stackPaneSupplier.setPrefHeight(newValue.doubleValue());
            stackPaneImportProduct.setPrefHeight(newValue.doubleValue());
            stackPaneUser.setPrefHeight(newValue.doubleValue());
            stackPaneCustomer.setPrefHeight(newValue.doubleValue());
            stackPaneBill.setPrefHeight(newValue.doubleValue());
            stackPanePromotion.setPrefHeight(newValue.doubleValue());
            stackPaneRevenueReport.setPrefHeight(newValue.doubleValue());
        });


        scrollPanePet.heightProperty().addListener((observable, oldValue, newValue) -> {
            tableViewPet.setPrefHeight(newValue.doubleValue());
        });

        scrollPaneProduct.heightProperty().addListener((observable, oldValue, newValue) -> {
            tableViewProduct.setPrefHeight(newValue.doubleValue());
        });

        scrollPaneSupplier.heightProperty().addListener((observable, oldValue, newValue) -> {
            tableViewSupplier.setPrefHeight(newValue.doubleValue());
        });

        scrollPaneLeftImportProduct.heightProperty().addListener((observable, oldValue, newValue) -> {
            tableViewLeftImportProduct.setPrefHeight(newValue.doubleValue());
        });

        scrollPaneUser.heightProperty().addListener((observable, oldValue, newValue) -> {
            tableViewUser.setPrefHeight(newValue.doubleValue());
        });

        scrollPaneCustomer.heightProperty().addListener((observable, oldValue, newValue) -> {
            tableViewCustomer.setPrefHeight(newValue.doubleValue());
        });

        scrollPaneBill.heightProperty().addListener((observable, oldValue, newValue) -> {
            tableViewBill.setPrefHeight(newValue.doubleValue());
        });

        scrollPanePromotion.heightProperty().addListener((observable, oldValue, newValue) -> {
            tableViewPromotion.setPrefHeight(newValue.doubleValue());
        });

        scrollPaneRevenueReport.heightProperty().addListener((observable, oldValue, newValue) -> {
            tableViewRevenueReport.setPrefHeight(newValue.doubleValue());
        });

        scrollPaneRightImportProduct.heightProperty().addListener((observable, oldValue, newValue) -> {
            tableViewRightImportProduct.setPrefHeight(newValue.doubleValue());
        });


        vBoxLeftImportProduct.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPaneLeftImportProduct.setPrefHeight(newValue.doubleValue());
        });

        vBoxRightImportProduct.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPaneRightImportProduct.setPrefHeight(newValue.doubleValue());
        });

        gridPaneImportProduct.heightProperty().addListener((observable, oldValue, newValue) -> {
            vBoxLeftImportProduct.setPrefHeight(newValue.doubleValue());
            vBoxRightImportProduct.setPrefHeight(newValue.doubleValue());
        });
    }

    private void autoResizeColumns(TableView<?> tableView) {
        for (TableColumn<?, ?> column : tableView.getColumns()) {
            Text tempText = new Text(column.getText()); // bắt đầu với header
            double max = tempText.getLayoutBounds().getWidth();

            for (int i = 0; i < tableView.getItems().size(); i++) {
                Object cellData = column.getCellData(i);
                if (cellData != null) {
                    tempText = new Text(cellData.toString());
                    double width = tempText.getLayoutBounds().getWidth();
                    if (width > max) {
                        max = width;
                    }
                }
            }
            column.setPrefWidth(max + 20); // thêm padding cho đẹp
        }
    }

    public void PetTable() {


        tableColumnIDPet.setCellValueFactory(cellData -> new SimpleStringProperty("PE" + cellData.getValue().getId()));
        tableColumnNamePet.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        tableColumnBreedPet.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Dog) {
                return new SimpleStringProperty(((Dog) cellData.getValue()).getBreed().getBreed());
            } else if (cellData.getValue() instanceof Cat) {
                return new SimpleStringProperty(((Cat) cellData.getValue()).getBreed().getBreed());
            } else if (cellData.getValue() instanceof Hamster) {
                return new SimpleStringProperty(((Hamster) cellData.getValue()).getBreed().getBreed());
            } else if (cellData.getValue() instanceof Rabbit) {
                return new SimpleStringProperty(((Rabbit) cellData.getValue()).getBreed().getBreed());
            } else
                return new SimpleStringProperty("");
        });
        tableColumnAgePet.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        tableColumnGenderPet.setCellValueFactory(cellData -> {
            if (cellData.getValue().isGender()) {
                return new SimpleStringProperty("Đực");
            } else {
                return new SimpleStringProperty("Cái");
            }
        });
        tableColumnSupplierPet.setCellValueFactory(cellData -> {


            return null;  // gắn lệnh lấy tên supplier từ ID vào đây


        });
        tableColumnPricePet.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        tableColumnVaccinatedPet.setCellValueFactory(cellData -> cellData.getValue().vaccinatedProperty().asObject());
        tableColumnHealthStatusPet.setCellValueFactory(cellData -> cellData.getValue().healthStatusProperty());
        tableColumnOriginPet.setCellValueFactory(cellData -> cellData.getValue().originProperty());
        tableColumnWeightPet.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        tableColumnFurColorPet.setCellValueFactory(cellData -> cellData.getValue().furColorProperty());
        tableColumnIsTrainedPet.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Dog) {
                boolean trained = ((Dog) cellData.getValue()).isTrainedProperty().get();
                String status = trained ? "Đã huấn luyện" : "Chưa huấn luyện";
                return new SimpleStringProperty(status);
            } else
                return new SimpleStringProperty("");
        });
        tableColumnIsIndoorPet.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Cat) {
                boolean indoor = ((Cat) cellData.getValue()).isIndoorProperty().get();
                String status = indoor ? "Trong nhà" : "Không trong nhà";
                return new SimpleStringProperty(status);
            } else
                return new SimpleStringProperty("");
        });

        tableColumnEyeColorPet.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Cat) {
                return new SimpleStringProperty(((Cat) cellData.getValue()).getEyeColor());
            } else
                return new SimpleStringProperty("");
        });

        tableColumnTailLengthPet.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Hamster) {
                return ((Hamster) cellData.getValue()).tailLengthProperty().asString();
            } else
                return new SimpleStringProperty("");
        });

        tableColumnEarLengthPet.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Rabbit) {
                return ((Rabbit) cellData.getValue()).earLengthProperty().asString();
            } else
                return new SimpleStringProperty("");
        });

        ObservableList<Pet> petList = FXCollections.observableArrayList();

        Dog dog1 = new Dog(1, "Bun", 2, true, 150.0, true, "Khỏe mạnh", "Việt Nam", 5.2, "Nâu", "Thân thiện", 1, EDogBreed.CauVang, true);
        Cat cat1 = new Cat(2, "Meo", 1, false, 100.0, true, "Khỏe mạnh", "Việt Nam", 4.0, "Trắng", "Dễ thương", 2, ECatBreed.MeoCam, true, "cam");

        petList.addAll(dog1, cat1);  // dùng vòng lập để truy xuất dữ liệu từ bảng Pet

        tableViewPet.setItems(petList);    // bỏ list vào đây

        autoResizeColumns(tableViewPet);

        tableViewPet.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void ProductTable() {
        tableColumnIDProduct.setCellValueFactory(cellData -> new SimpleStringProperty("PD" + cellData.getValue().getId()));

        tableColumnNameProduct.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        tableColumnCategoriesProduct.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Food) {
                return new SimpleStringProperty("Food");
            } else if (cellData.getValue() instanceof Toy) {
                return new SimpleStringProperty("Toy");
            } else if (cellData.getValue() instanceof Cage) {
                return new SimpleStringProperty("Cage");
            } else if (cellData.getValue() instanceof Accessory) {
                return new SimpleStringProperty("Accessory");
            } else return new SimpleStringProperty("");
        });

        tableColumnPriceProduct.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        tableColumnQuantityProduct.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        tableColumnExpirationDateProduct.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Food) {
                return ((Food) cellData.getValue()).expirationDateProperty().asString();
            } else return new SimpleStringProperty("");
        });

        tableColumnFlavorProduct.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Food) {
                return ((Food) cellData.getValue()).flavorProperty();
            } else return new SimpleStringProperty("");
        });

        tableColumnMaterialProduct.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Toy) {
                return ((Toy) cellData.getValue()).materialProperty();
            } else if (cellData.getValue() instanceof Cage) {
                return ((Cage) cellData.getValue()).materialProperty();
            } else return new SimpleStringProperty("");
        });

        tableColumnSizeProduct.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Toy) {
                return ((Toy) cellData.getValue()).sizeProperty();
            } else return new SimpleStringProperty("");
        });

        tableColumnDimensionProduct.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Cage) {
                return ((Cage) cellData.getValue()).dimensionProperty();
            } else return new SimpleStringProperty("");
        });

        tableColumnTypeProduct.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Accessory) {
                return ((Accessory) cellData.getValue()).typeProperty();
            } else return new SimpleStringProperty("");
        });

        tableColumnBrandProduct.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Accessory) {
                return ((Accessory) cellData.getValue()).brandProperty();
            } else return new SimpleStringProperty("");
        });

        tableColumnSupplierProduct.setCellValueFactory(cellData -> {

            return null;   // bỏ lệnh lấy tên supplier từ id vào đây

        });

        tableColumnManufacturerProduct.setCellValueFactory(cellData -> cellData.getValue().manufacturerProperty());

        ObservableList<Product> productList = FXCollections.observableArrayList();

        Food food1 = new Food(1, "nanna", 120000, 20, "con chó Khôi", 1, "babela", LocalDate.of(2005, 12, 31), "cá ngừ");

        Toy toy1 = new Toy(2, "Pet toy", 100000, 50, "cho chó chơi", 2, "haheha", "plastic", "100x100");

        Cage cage1 = new Cage(3, "Chuồng chó", 200000, 30, "dành cho người", 2, "hay hay", "2000x5000x2000", "inox");

        productList.addAll(cage1, food1, toy1);    // dùng vòng lập để truy xuất dữ liệu từ bảng Product

        tableViewProduct.setItems(productList);    // bỏ list vào đây

        autoResizeColumns(tableViewProduct);

        tableViewProduct.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void SupplierTable() {
        tableColumnIDSupplier.setCellValueFactory(cellData -> new SimpleStringProperty("SL" + cellData.getValue().getId()));

        tableColumnNameSupplier.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        tableColumnAddressSupplier.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

        tableColumnPhoneNumberSupplier.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());

        tableColumnEmailSupplier.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

        Supplier supplier1 = new Supplier(1, "Trang Kim Đạt", "450 Lê Văn Việt", "0396290084", "trangkimdatst2005@gmail.com");
        Supplier supplier2 = new Supplier(2, "Đinh Công Vinh Quang", "448 Lê Văn Việt", "0355566144", "quang@gmail.com");
        Supplier supplier3 = new Supplier(2, "Bùi Thế Vinh", "451 Lê Văn Việt", "0316556511", "vinh@gmail.com");

        supplierList.addAll(supplier1, supplier2, supplier3);

        tableViewSupplier.setItems(supplierList);

        autoResizeColumns(tableViewSupplier);

        tableViewSupplier.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void SupplierImportTable() {

        // left
        tableColumnLeftIDImportProduct.setCellValueFactory(cellData -> new SimpleStringProperty("PD" + cellData.getValue().getId()));

        tableColumnLeftNameImportProduct.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        tableColumnLeftPriceImportProduct.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        tableColumnLeftQuantityImportProduct.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());


        tableColumnRightIDImportProduct.setCellValueFactory(cellData -> new SimpleStringProperty("PD" + cellData.getValue().getId()));

        tableColumnRightNameImportProduct.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        tableColumnRightPriceImportProduct.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        tableColumnRightQuantityImportProduct.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        tableColumnRightTotalPriceImportProduct.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper<>(cellData.getValue().priceProperty().get() * cellData.getValue().quantityProperty().get());
        });

        tableColumnRightNumericalOrderImportProduct.setCellFactory(column -> new TableCell<Product, Void>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1));
                }
            }
        });

        tableViewLeftImportProduct.getItems().addAll(tableViewProduct.getItems());

        autoResizeColumns(tableViewLeftImportProduct);

        tableViewLeftImportProduct.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        autoResizeColumns(tableViewRightImportProduct);

        tableViewRightImportProduct.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void UserTable() {
        tableColumnIDUser.setCellValueFactory(cellData -> new SimpleStringProperty("US" + cellData.getValue().getId()));
        tableColumnNameUser.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tableColumnGenderUser.setCellValueFactory(cellData -> {
            if (cellData.getValue().isGender()) {
                return new SimpleStringProperty("Nam");
            } else {
                return new SimpleStringProperty("Nữ");
            }
        });

        tableColumnUsernameUser.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        tableColumnPasswordUser.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        tableColumnEmailUser.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        tableColumnAddressUser.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        tableColumnCreateAtUser.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getCreationDate()).asString());
        tableColumnBirthDateUser.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getBirthDay()).asString());
        tableColumnPositionUser.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Employee) {
                return ((Employee) cellData.getValue()).positionProperty();
            } else {
                return null;
            }
        });
        tableColumnSalaryUser.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Employee) {
                return ((Employee) cellData.getValue()).salaryProperty().asObject();
            } else {
                return null;
            }
        });
        tableColumnWorkingHoursUser.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Employee) {
                return ((Employee) cellData.getValue()).workingHoursProperty();
            } else {
                return null;
            }
        });

        ObservableList<User> userList = FXCollections.observableArrayList();

        User user = new Employee(1, "username", "password", "Trang Kim Đạt", true, "trangkimdatst2005@gmail.com", "0396290084", "448 Lê Văn Việt", LocalDate.of(2005, 9, 16), LocalDate.now(), "Chủ tịch", 500000000, "Full time");

        userList.add(user);

        tableViewUser.setItems(userList);

        autoResizeColumns(tableViewUser);

        tableViewUser.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void CustomerTable() {
        tableColumnIDCustomer.setCellValueFactory(cellData -> new SimpleStringProperty("CS" + cellData.getValue().getId()));
        tableColumnNameCustomer.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tableColumnPhoneNumberCustomer.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());

        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        Customer customer = new Customer(1, "Bùi Thế Vinh", "0355648921");
        Customer customer1 = new Customer(2, "Đinh Công Vinh Quang", "0864231564");

        customerList.addAll(customer, customer1);

        tableViewCustomer.setItems(customerList);

        autoResizeColumns(tableViewCustomer);

        tableViewCustomer.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void BillTable() {
        tableColumnIDBill.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf("BI" + cellData.getValue().getId())));
        tableColumnCustomerBill.setCellValueFactory(cellData -> {
            return null;   // lệnh lấy tên từ customer
        });
        tableColumnEmployeeBill.setCellValueFactory(cellData -> {
            return null;  // lệnh lấy tên từ employee
        });
        tableColumnDateBill.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getInvoiceDate()).asString());
        tableColumnTotalAmountBill.setCellValueFactory(cellData -> cellData.getValue().totalAmountProperty().asObject());
        tableColumnPaymentMethodBill.setCellValueFactory(cellData -> cellData.getValue().paymentMethodProperty());
        tableColumnStatusBill.setCellValueFactory(cellData -> cellData.getValue().statusProperty());


        ObservableList<Bill> billList = FXCollections.observableArrayList();

        Bill bill = new Bill(1, 1, 1, LocalDate.now(), 1000000, "pay", "thành công");

        billList.addAll(bill);

        tableViewBill.setItems(billList);

        autoResizeColumns(tableViewBill);

        tableViewBill.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void PromotionTable() {
        tableColumnIDPromotion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf("PM" + cellData.getValue().getId())));
        tableColumnNamePromotion.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tableColumnStartDatePromotion.setCellValueFactory(cellData -> cellData.getValue().startDateProperty().asString());
        tableColumnEndDatePromotion.setCellValueFactory(cellData -> cellData.getValue().endDateProperty().asString());
        tableColumnDiscountPercentPromotion.setCellValueFactory(cellData -> cellData.getValue().discountPercentProperty().asObject());
        tableColumnDiscountPercentagePromotion.setCellValueFactory(cellData -> cellData.getValue().discountPercentageProperty().asObject());
        tableColumnIsActivePromotion.setCellValueFactory(cellData -> {
            if (cellData.getValue().isIsActive()) {
                return new SimpleStringProperty("Đã đươc kích hoạt");
            } else {
                return new SimpleStringProperty("Chưa được kích hoạt");
            }
        });
        tableColumnNotePromotion.setCellValueFactory(cellData -> new SimpleStringProperty(""));

        ObservableList<Promotion> promotionList = FXCollections.observableArrayList();

        Promotion promotion = new Promotion(1, "mua 3 tặng 1", 100, LocalDate.now(), LocalDate.now().plusMonths(1), 100, true);

        promotionList.add(promotion);

        tableViewPromotion.setItems(promotionList);

        autoResizeColumns(tableViewPromotion);

        tableViewPromotion.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void RevenueReportTable() {
        tableColumnIDRevenueReport.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf("RR" + cellData.getValue().getId())));
        tableColumnMonthRevenueReport.setCellValueFactory(cellData -> cellData.getValue().monthProperty().asObject());
        tableColumnYearRevenueReport.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        tableColumnTotalRevenueRevenueReport.setCellValueFactory(cellData -> cellData.getValue().totalRevenueProperty().asObject());
        tableColumnTotalBillRevenueReport.setCellValueFactory(cellData -> cellData.getValue().totalBillProperty().asObject());
        tableColumnNoteRevenueReport.setCellValueFactory(cellData -> new SimpleStringProperty(""));

        ObservableList<RevenueReport> revenueReportList = FXCollections.observableArrayList();

        RevenueReport revenueReport = new RevenueReport(1,1,2000,1000000000,1000);
        revenueReportList.add(revenueReport);

        tableViewRevenueReport.setItems(revenueReportList);

        autoResizeColumns(tableViewRevenueReport);

        tableViewRevenueReport.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // ẩn hiện màng hình
        hideScreen();

        // bắt đầu sự kiện Pet
        actionPet(null);

        // chỉnh độ cao của table view
        heightAdjustment();

        // bỏ dữ liệu vào bảng Pet
        PetTable();

        // bỏ dữ liệu vào bảng product
        ProductTable();

        //  bỏ dữ liệu vào bảng supplier
        SupplierTable();

        // bỏ dữ liệu vào bảng importSupplier
        SupplierImportTable();

        // bỏ dữ liệu vào bảng user
        UserTable();

        // bỏ dữ liệu vào bảng customer
        CustomerTable();

        // bỏ dữ liệu vào bảng bill
        BillTable();

        // bỏ dữ liệu vào bảng promotion
        PromotionTable();

        // bỏ dữ liệu vào bảng RevenueReport
        RevenueReportTable();


    }
}

