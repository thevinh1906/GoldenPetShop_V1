package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Pet.Pet;
import com.utc2.petShop.model.entities.Product.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerProduct implements Initializable {

    @FXML
    private ScrollPane ScrollCenter;

    @FXML
    private VBox VBoxSrcollCenter;

    @FXML
    private GridPane gridPaneAccessory;

    @FXML
    private GridPane gridPaneCage;

    @FXML
    private GridPane gridPaneFood;

    @FXML
    private GridPane gridPaneGeneral;

    @FXML
    private GridPane gridPaneToy;

    @FXML
    private ImageView inageViewPet;

    @FXML
    private Label labelBrandAccessory;

    @FXML
    private Label labelDescribe;

    @FXML
    private Label labelDimensionCage;

    @FXML
    private Label labelExpirationDateFood;

    @FXML
    private Label labelFlavorFood;

    @FXML
    private Label labelGiaBan;

    @FXML
    private Label labelManufacturerGeneral;

    @FXML
    private Label labelMaterialCage;

    @FXML
    private Label labelMaterialToy;

    @FXML
    private Label labelNameGeneral;

    @FXML
    private Label labelProductIDGeneral;

    @FXML
    private Label labelQuantityGeneral;

    @FXML
    private Label labelSizeToy;

    @FXML
    private Label labelSupplierGeneral;

    @FXML
    private Label labelTypeAccessory;

    @FXML
    private ImageView logoImg;

    public void hideScreen() {
        gridPaneFood.setVisible(false);
        gridPaneCage.setVisible(false);
        gridPaneAccessory.setVisible(false);
        gridPaneToy.setVisible(false);

        gridPaneFood.setManaged(false);
        gridPaneCage.setManaged(false);
        gridPaneToy.setManaged(false);
        gridPaneAccessory.setManaged(false);
    }

    public void receiveData(Product obj) {
        labelGiaBan.setText(String.valueOf(obj.getPrice() + "$"));
        labelDescribe.setText(obj.getDescription());
        labelProductIDGeneral.setText(String.valueOf("PD" + obj.getId()));
        labelNameGeneral.setText(obj.getName());
        labelQuantityGeneral.setText(String.valueOf(obj.getQuantity()));
        labelManufacturerGeneral.setText(obj.getManufacturer());
        labelSupplierGeneral.setText(String.valueOf(obj.getSupplier()));
        if(obj instanceof Food){
            Food food = (Food) obj;
            labelExpirationDateFood.setText(String.valueOf(food.getExpirationDate()));
            labelFlavorFood.setText(String.valueOf(food.getFlavor()));

            hideScreen();

            gridPaneFood.setManaged(true);
            gridPaneFood.setVisible(true);
        } else if (obj instanceof Toy) {
            Toy toy = (Toy) obj;
            labelMaterialToy.setText(toy.getMaterial());
            labelSizeToy.setText(String.valueOf(toy.getSize()));

            hideScreen();
            gridPaneToy.setManaged(true);
            gridPaneToy.setVisible(true);
        }
        else if (obj instanceof Cage){
            Cage cage = (Cage) obj;
            labelMaterialCage.setText(cage.getMaterial());
            labelDimensionCage.setText(cage.getDimension());

            hideScreen();
            gridPaneCage.setManaged(true);
            gridPaneCage.setVisible(true);
        }
        else if (obj instanceof Accessory){
            Accessory accessory = (Accessory) obj;
            labelBrandAccessory.setText(accessory.getBrand());
            labelTypeAccessory.setText(accessory.getType());

            hideScreen();

            gridPaneAccessory.setManaged(true);
            gridPaneAccessory.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hideScreen();
    }
}
