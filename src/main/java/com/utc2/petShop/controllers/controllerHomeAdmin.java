package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Pet.*;
import com.utc2.petShop.model.entities.Product.Product;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

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
    private Button buttonBack;

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
    private Button buttonForward;

    @FXML
    private Button buttonImportPet;

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
    private TableColumn<?, ?> tableColumnAddressSupplier;

    @FXML
    private TableColumn<?, ?> tableColumnAddressUser;

    @FXML
    private TableColumn<Pet, Integer> tableColumnAgePet;

    @FXML
    private TableColumn<?, ?> tableColumnBirthDateUser;

    @FXML
    private TableColumn<Product, String> tableColumnBrandProduct;

    @FXML
    private TableColumn<Pet, String> tableColumnBreedPet;

    @FXML
    private TableColumn<?, ?> tableColumnCreateAtUser;

    @FXML
    private TableColumn<?, ?> tableColumnCustomerIDBill;

    @FXML
    private TableColumn<?, ?> tableColumnDateBill;

    @FXML
    private TableColumn<Product, String> tableColumnDimensionProduct;

    @FXML
    private TableColumn<?, ?> tableColumnDiscountPercentPromotion;

    @FXML
    private TableColumn<?, ?> tableColumnDiscountPercentagePromotion;

    @FXML
    private TableColumn<Pet, String> tableColumnEarLengthPet;

    @FXML
    private TableColumn<?, ?> tableColumnEmailSupplier;

    @FXML
    private TableColumn<?, ?> tableColumnEmailUser;

    @FXML
    private TableColumn<?, ?> tableColumnEmployeeBill;

    @FXML
    private TableColumn<?, ?> tableColumnEndDatePromotion;

    @FXML
    private TableColumn<Product, LocalDate> tableColumnExpirationDateProduct;

    @FXML
    private TableColumn<Pet, String> tableColumnEyeColorPet;

    @FXML
    private TableColumn<Product, String> tableColumnFlavorProduct;

    @FXML
    private TableColumn<Pet, String> tableColumnFurColorPet;

    @FXML
    private TableColumn<Pet, String> tableColumnGenderPet;

    @FXML
    private TableColumn<?, ?> tableColumnGenderUser;

    @FXML
    private TableColumn<Pet, String> tableColumnHealthStatusPet;

    @FXML
    private TableColumn<?, ?> tableColumnIDBill;

    @FXML
    private TableColumn<?, ?> tableColumnIDCustomer;

    @FXML
    private TableColumn<Pet, String> tableColumnIDPet;

    @FXML
    private TableColumn<Product, Integer> tableColumnIDProduct;

    @FXML
    private TableColumn<?, ?> tableColumnIDPromotion;

    @FXML
    private TableColumn<?, ?> tableColumnIDRevenueReport;

    @FXML
    private TableColumn<?, ?> tableColumnIDSupplier;

    @FXML
    private TableColumn<?, ?> tableColumnIDUser;

    @FXML
    private TableColumn<?, ?> tableColumnIsActivePromotion;

    @FXML
    private TableColumn<Pet, String> tableColumnIsIndoorPet;

    @FXML
    private TableColumn<Pet, String> tableColumnIsTrainedPet;

    @FXML
    private TableColumn<?, ?> tableColumnLeftIDImportProduct;

    @FXML
    private TableColumn<?, ?> tableColumnLeftNameImportProduct;

    @FXML
    private TableColumn<?, ?> tableColumnLeftPriceImportProduct;

    @FXML
    private TableColumn<?, ?> tableColumnLeftQuantityImportProduct;

    @FXML
    private TableColumn<Product, String> tableColumnManufacturerProduct;

    @FXML
    private TableColumn<?, ?> tableColumnManufacturerSupplier;

    @FXML
    private TableColumn<Product, String> tableColumnMaterialProduct;

    @FXML
    private TableColumn<?, ?> tableColumnMonthRevenueReport;

    @FXML
    private TableColumn<?, ?> tableColumnNameCustomer;

    @FXML
    private TableColumn<Pet, String> tableColumnNamePet;

    @FXML
    private TableColumn<Product, String> tableColumnNameProduct;

    @FXML
    private TableColumn<?, ?> tableColumnNamePromotion;

    @FXML
    private TableColumn<?, ?> tableColumnNameSupplier;

    @FXML
    private TableColumn<?, ?> tableColumnNameUser;

    @FXML
    private TableColumn<Product, String> tableColumnNoteProduct;

    @FXML
    private TableColumn<?, ?> tableColumnNotePromotion;

    @FXML
    private TableColumn<?, ?> tableColumnNoteRevenueReport;

    @FXML
    private TableColumn<?, ?> tableColumnNoteUser;

    @FXML
    private TableColumn<Pet, String> tableColumnNotePet;

    @FXML
    private TableColumn<Pet, String> tableColumnOriginPet;

    @FXML
    private TableColumn<?, ?> tableColumnPasswordUser;

    @FXML
    private TableColumn<?, ?> tableColumnPaymentMethodBill;

    @FXML
    private TableColumn<?, ?> tableColumnPhoneNumberCustomer;

    @FXML
    private TableColumn<?, ?> tableColumnPhoneNumberSupplier;

    @FXML
    private TableColumn<?, ?> tableColumnPositionUser;

    @FXML
    private TableColumn<Pet, Double> tableColumnPricePet;

    @FXML
    private TableColumn<Product, Double> tableColumnPriceProduct;

    @FXML
    private TableColumn<?, ?> tableColumnQuantityBill;

    @FXML
    private TableColumn<Product, Integer> tableColumnQuantityProduct;

    @FXML
    private TableColumn<?, ?> tableColumnRightIDImportProduct;

    @FXML
    private TableColumn<?, ?> tableColumnRightNameImportProduct;

    @FXML
    private TableColumn<?, ?> tableColumnRightNumericalOrderImportProduct;

    @FXML
    private TableColumn<?, ?> tableColumnRightPriceImportProduct;

    @FXML
    private TableColumn<?, ?> tableColumnRightQuantityImportProduct;

    @FXML
    private TableColumn<?, ?> tableColumnSalaryUser;

    @FXML
    private TableColumn<Product, String> tableColumnSizeProduct;

    @FXML
    private TableColumn<?, ?> tableColumnStartDatePromotion;

    @FXML
    private TableColumn<?, ?> tableColumnStatusBill;

    @FXML
    private TableColumn<Pet, String> tableColumnSupplierPet;

    @FXML
    private TableColumn<Product, String> tableColumnSupplierProduct;

    @FXML
    private TableColumn<Pet, String> tableColumnTailLengthPet;

    @FXML
    private TableColumn<?, ?> tableColumnTotalAmountBill;

    @FXML
    private TableColumn<?, ?> tableColumnTotalBillRevenueReport;

    @FXML
    private TableColumn<?, ?> tableColumnTotalRevenueRevenueReport;

    @FXML
    private TableColumn<Product, String> tableColumnTypeProduct;

    @FXML
    private TableColumn<?, ?> tableColumnUsernameUser;

    @FXML
    private TableColumn<Pet, Boolean> tableColumnVaccinatedPet;

    @FXML
    private TableColumn<Pet, Double> tableColumnWeightPet;

    @FXML
    private TableColumn<?, ?> tableColumnWorkingHoursUser;

    @FXML
    private TableColumn<?, ?> tableColumnYearRevenueReport;

    @FXML
    private TableView<?> tableViewBill;

    @FXML
    private TableView<?> tableViewCustomer;

    @FXML
    private TableView<?> tableViewLeftImportProduct;

    @FXML
    private TableView<Pet> tableViewPet;

    @FXML
    private TableView<Product> tableViewProduct;

    @FXML
    private TableView<?> tableViewPromotion;

    @FXML
    private TableView<?> tableViewRevenueReport;

    @FXML
    private TableView<?> tableViewRightImportProduct;

    @FXML
    private TableView<?> tableViewSupplier;

    @FXML
    private TableView<?> tableViewUser;

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
    void actionAddPet(ActionEvent event) {

    }

    @FXML
    void actionAddProduct(ActionEvent event) {

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

        stackPaneBill.setVisible(true);
        stackPaneBill.setManaged(true);

        stackPanePromotion.setVisible(false);
        stackPanePromotion.setManaged(false);

        stackPaneRevenueReport.setVisible(false);
        stackPaneRevenueReport.setManaged(false);
    }

    @FXML
    void actionCustomer(ActionEvent event) {
        stackPaneUser.setManaged(false);
        stackPaneUser.setVisible(false);

        stackPaneCustomer.setManaged(true);
        stackPaneCustomer.setVisible(true);

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
    void actionImportPet(ActionEvent event) {

    }

    @FXML
    void actionImportProduct(ActionEvent event) {
        stackPaneUser.setManaged(false);
        stackPaneUser.setVisible(false);

        stackPaneCustomer.setManaged(false);
        stackPaneCustomer.setVisible(false);

        stackPaneImportProduct.setVisible(true);
        stackPaneImportProduct.setManaged(true);

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

    @FXML
    void actionImportProductImportProduct(ActionEvent event) {

    }

    @FXML
    void actionLeftAddImportProduct(ActionEvent event) {

    }

    @FXML
    void actionPet(ActionEvent event) {
        stackPaneUser.setManaged(false);
        stackPaneUser.setVisible(false);

        stackPaneCustomer.setManaged(false);
        stackPaneCustomer.setVisible(false);

        stackPaneImportProduct.setVisible(false);
        stackPaneImportProduct.setManaged(false);

        stackPanePet.setVisible(true);
        stackPanePet.setManaged(true);

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

    @FXML
    void actionProduct(ActionEvent event) {
        stackPaneUser.setManaged(false);
        stackPaneUser.setVisible(false);

        stackPaneCustomer.setManaged(false);
        stackPaneCustomer.setVisible(false);

        stackPaneImportProduct.setVisible(false);
        stackPaneImportProduct.setManaged(false);

        stackPanePet.setVisible(false);
        stackPanePet.setManaged(false);

        stackPaneProduct.setVisible(true);
        stackPaneProduct.setManaged(true);

        stackPaneSupplier.setVisible(false);
        stackPaneSupplier.setManaged(false);

        stackPaneBill.setVisible(false);
        stackPaneBill.setManaged(false);

        stackPanePromotion.setVisible(false);
        stackPanePromotion.setManaged(false);

        stackPaneRevenueReport.setVisible(false);
        stackPaneRevenueReport.setManaged(false);
    }

    @FXML
    void actionPromotion(ActionEvent event) {
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

        stackPanePromotion.setVisible(true);
        stackPanePromotion.setManaged(true);

        stackPaneRevenueReport.setVisible(false);
        stackPaneRevenueReport.setManaged(false);
    }

    @FXML
    void actionRevenueReport(ActionEvent event) {
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

        stackPaneSupplier.setVisible(true);
        stackPaneSupplier.setManaged(true);

        stackPaneBill.setVisible(false);
        stackPaneBill.setManaged(false);

        stackPanePromotion.setVisible(false);
        stackPanePromotion.setManaged(false);

        stackPaneRevenueReport.setVisible(false);
        stackPaneRevenueReport.setManaged(false);
    }

    @FXML
    void actionUser(ActionEvent event) {
        stackPaneUser.setManaged(true);
        stackPaneUser.setVisible(true);

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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



        //bắt đầu sự kiện Pet

        actionPet(null);



        // chỉnh độ cao của table view
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



        // bỏ dữ liệu vào bảng Pet

        tableColumnIDPet.setCellValueFactory(cellData -> new SimpleStringProperty("P" + cellData.getValue().getId()));
        tableColumnNamePet.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        tableColumnBreedPet.setCellValueFactory(cellData -> {
            if(cellData.getValue() instanceof Dog){
                return new SimpleStringProperty(((Dog) cellData.getValue()).getBreed().getBreed());
            }
            else if(cellData.getValue() instanceof Cat){
                return new SimpleStringProperty(((Cat) cellData.getValue()).getBreed().getBreed());
            }
            else if(cellData.getValue() instanceof Hamster){
                return new SimpleStringProperty(((Hamster) cellData.getValue()).getBreed().getBreed());
            }
            else if(cellData.getValue() instanceof Rabbit){
                return new SimpleStringProperty(((Rabbit) cellData.getValue()).getBreed().getBreed());
            }
            else
                return new SimpleStringProperty("");
        });
        tableColumnAgePet.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        tableColumnGenderPet.setCellValueFactory(cellData -> {
            if(cellData.getValue().isGender()){
                return new SimpleStringProperty("Đực");
            }
            else {
                return new SimpleStringProperty("Cái");
            }
        });
//        tableColumnSupplierPet.setCellValueFactory(cellData -> {


           // lệnh truy xuất lấy tên Supplier


//        });
        tableColumnPricePet.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        tableColumnVaccinatedPet.setCellValueFactory(cellData -> cellData.getValue().vaccinatedProperty().asObject());
        tableColumnHealthStatusPet.setCellValueFactory(cellData -> cellData.getValue().healthStatusProperty());
        tableColumnOriginPet.setCellValueFactory(cellData -> cellData.getValue().originProperty());
        tableColumnWeightPet.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        tableColumnFurColorPet.setCellValueFactory(cellData -> cellData.getValue().furColorProperty());
        tableColumnIsTrainedPet.setCellValueFactory(cellData -> {
            if(cellData.getValue() instanceof Dog){
                boolean trained = ((Dog) cellData.getValue()).isTrainedProperty().get();
                String status = trained ? "Đã huấn luyện" : "Chưa huấn luyện";
                return new SimpleStringProperty(status);
            }
            else
                return new SimpleStringProperty("");
        });
        tableColumnIsIndoorPet.setCellValueFactory(cellData -> {
            if(cellData.getValue() instanceof Cat){
                boolean indoor = ((Cat) cellData.getValue()).isIndoorProperty().get();
                String status = indoor ? "Trong nhà" : "Không trong nhà";
                return new SimpleStringProperty(status);
            }
            else
                return new SimpleStringProperty("");
        });

        tableColumnEyeColorPet.setCellValueFactory(cellData -> {
            if(cellData.getValue() instanceof Cat){
                return new SimpleStringProperty(((Cat) cellData.getValue()).getEyeColor());
            }
            else
                return new SimpleStringProperty("");
        });

        tableColumnTailLengthPet.setCellValueFactory(cellData -> {
            if(cellData.getValue() instanceof Hamster){
                return ((Hamster) cellData.getValue()).tailLengthProperty().asString();
            }
            else
                return new SimpleStringProperty("");
        });

        tableColumnEarLengthPet.setCellValueFactory(cellData -> {
            if(cellData.getValue() instanceof Rabbit){
                return ((Rabbit) cellData.getValue()).earLengthProperty().asString();
            }
            else
                return new SimpleStringProperty("");
        });

        ObservableList<Pet> petList = FXCollections.observableArrayList();

        Dog dog1 = new Dog(1, "Bun", 2, true, 150.0, true, "Khỏe mạnh", "Việt Nam", 5.2, "Nâu", "Thân thiện", 1, EDogBreed.CauVang, true);
        Cat cat1 = new Cat(2, "Meo", 1, false, 100.0, true, "Khỏe mạnh", "Việt Nam", 4.0, "Trắng", "Dễ thương", 2, ECatBreed.MeoCam, true, "cam");

        petList.addAll(dog1, cat1);

        tableViewPet.setItems(petList);



        //bỏ dữ liệu vào bảng product

    }
}

