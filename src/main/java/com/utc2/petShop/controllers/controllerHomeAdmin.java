package com.utc2.petShop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerHomeAdmin implements Initializable {

    @FXML
    private ScrollPane ScrollCenter;

    @FXML
    private ScrollPane SrcollLeft;

    @FXML
    private BorderPane borderPanePet;

    @FXML
    private BorderPane borderPaneProduct;

    @FXML
    private BorderPane borderPaneSupplier;

    @FXML
    private Button buttonAccount;

    @FXML
    private Button buttonAddPet;

    @FXML
    private Button buttonAddProduct;

    @FXML
    private Button buttonAddSupplier;

    @FXML
    private Button buttonArrangePet;

    @FXML
    private Button buttonArrangeProduct;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonBill;

    @FXML
    private Button buttonCustomer;

    @FXML
    private Button buttonDeletePet;

    @FXML
    private Button buttonDeleteProduct;

    @FXML
    private Button buttonDeleteSupplier;

    @FXML
    private Button buttonDetailPet;

    @FXML
    private Button buttonDetailProduct;

    @FXML
    private Button buttonEditPet;

    @FXML
    private Button buttonEditProduct;

    @FXML
    private Button buttonEditSupplier;

    @FXML
    private Button buttonExcelPet;

    @FXML
    private Button buttonExcelProduct;

    @FXML
    private Button buttonExcelSupplier;

    @FXML
    private Button buttonFilterPet;

    @FXML
    private Button buttonFilterProduct;

    @FXML
    private Button buttonFind;

    @FXML
    private Button buttonForward;

    @FXML
    private Button buttonImport;

    @FXML
    private Button buttonImportSlip;

    @FXML
    private Button buttonPet;

    @FXML
    private Button buttonProduct;

    @FXML
    private Button buttonPromotion;

    @FXML
    private Button buttonReport;

    @FXML
    private Button buttonSetting;

    @FXML
    private Button buttonSupplier;

    @FXML
    private Button buttonUser;

    @FXML
    private ImageView logoImg;

    @FXML
    private StackPane stackPanePet;

    @FXML
    private StackPane stackPaneProduct;

    @FXML
    private StackPane stackPaneSupplier;

    @FXML
    private TextField textSreach;

    @FXML
    void actionAccount(ActionEvent event) {

    }

    @FXML
    void actionAddPet(ActionEvent event) {

    }

    @FXML
    void actionAddProduct(ActionEvent event) {

    }

    @FXML
    void actionAddSupplier(ActionEvent event) {

    }

    @FXML
    void actionArrangePet(ActionEvent event) {

    }

    @FXML
    void actionArrangeProduct(ActionEvent event) {

    }

    @FXML
    void actionBack(ActionEvent event) {

    }

    @FXML
    void actionBill(ActionEvent event) {

    }

    @FXML
    void actionCustomer(ActionEvent event) {

    }

    @FXML
    void actionDeletePet(ActionEvent event) {

    }

    @FXML
    void actionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void actionDeleteSupplier(ActionEvent event) {

    }

    @FXML
    void actionDetailPet(ActionEvent event) {

    }

    @FXML
    void actionDetailProduct(ActionEvent event) {

    }

    @FXML
    void actionEditPet(ActionEvent event) {

    }

    @FXML
    void actionEditProduct(ActionEvent event) {

    }

    @FXML
    void actionEditSupplier(ActionEvent event) {

    }

    @FXML
    void actionExcelPet(ActionEvent event) {

    }

    @FXML
    void actionExcelProduct(ActionEvent event) {

    }

    @FXML
    void actionExcelSupplier(ActionEvent event) {

    }

    @FXML
    void actionFilterPet(ActionEvent event) {

    }

    @FXML
    void actionFilterProduct(ActionEvent event) {

    }

    @FXML
    void actionFind(ActionEvent event) {

    }

    @FXML
    void actionForward(ActionEvent event) {

    }

    @FXML
    void actionImport(ActionEvent event) {

    }

    @FXML
    void actionImportSlip(ActionEvent event) {

    }

    @FXML
    void actionPet(ActionEvent event) {
        stackPaneProduct.setVisible(false);
        stackPaneProduct.setManaged(false);

        stackPaneSupplier.setVisible(false);
        stackPaneSupplier.setManaged(false);

        stackPanePet.setVisible(true);
        stackPanePet.setManaged(true);
    }

    @FXML
    void actionProduct(ActionEvent event) {
        stackPaneSupplier.setManaged(false);
        stackPaneSupplier.setVisible(false);

        stackPanePet.setVisible(false);
        stackPanePet.setManaged(false);

        stackPaneProduct.setVisible(true);
        stackPaneProduct.setManaged(true);
    }

    @FXML
    void actionPromotion(ActionEvent event) {

    }

    @FXML
    void actionReport(ActionEvent event) {

    }

    @FXML
    void actionSetting(ActionEvent event) {

    }

    @FXML
    void actionSupplier(ActionEvent event) {
        stackPanePet.setVisible(false);
        stackPanePet.setManaged(false);

        stackPaneProduct.setVisible(false);
        stackPaneProduct.setManaged(false);

        stackPaneSupplier.setVisible(true);
        stackPaneSupplier.setManaged(true);
    }

    @FXML
    void actionUser(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stackPanePet.setVisible(false);
        stackPanePet.setManaged(false);

        stackPaneProduct.setVisible(false);
        stackPaneProduct.setManaged(false);

        stackPaneSupplier.setVisible(false);
        stackPaneSupplier.setManaged(false);
    }
}
