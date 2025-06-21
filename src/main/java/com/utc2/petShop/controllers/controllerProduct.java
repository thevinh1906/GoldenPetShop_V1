package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Image.ImageByte;
import com.utc2.petShop.model.entities.Product.*;
import com.utc2.petShop.utils.ImageUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class controllerProduct implements Initializable {

    @FXML
    private Button buttonLeft;

    @FXML
    private Button buttonRight;

    @FXML
    private GridPane gridPaneAccessory;

    @FXML
    private GridPane gridPaneCage;

    @FXML
    private GridPane gridPaneFood;

    @FXML
    private GridPane gridPaneToy;

    @FXML
    private HBox hBoxProductImage;

    @FXML
    private ImageView imageViewProduct;

    @FXML
    private Label labelBrand;

    @FXML
    private Label labelDescribe;

    @FXML
    private Label labelDimension;

    @FXML
    private Label labelExpirationDate;

    @FXML
    private Label labelFlavor;

    @FXML
    private Label labelID;

    @FXML
    private Label labelManufacturer;

    @FXML
    private Label labelMaterialCage;

    @FXML
    private Label labelMaterialToy;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPrice;

    @FXML
    private Label labelQuatity;

    @FXML
    private Label labelSize;

    @FXML
    private Label labelSupplier;

    @FXML
    private Label labelType;

    @FXML
    private Label labelTypeAccessory;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    void buttonLeftAction(ActionEvent event) {
        index[0] = (index[0] - 1 + images.size()) % images.size();
        imageViewProduct.setImage(ImageUtils.byteArrayToImage(images.get(index[0]).getImage()));
    }

    @FXML
    void buttonRightAction(ActionEvent event) {
        index[0] = (index[0] + 1) % images.size();
        imageViewProduct.setImage(ImageUtils.byteArrayToImage(images.get(index[0]).getImage()));
    }

    private List<ImageByte> images;

    int[] index = {0};


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
        images = obj.getImages();
        if (images != null && !images.isEmpty()) {
            imageViewProduct.setImage(ImageUtils.cropToImageView(ImageUtils.byteArrayToImage(images.get(index[0]).getImage()), 450, 450));
        } else {
            System.out.println("Danh sách ảnh rỗng hoặc null");
//            imageViewProduct.setImage(); // hoặc ảnh mặc định
        }
        labelName.setText(obj.getName());
        labelID.setText("PD" + String.valueOf(obj.getId()));
        labelPrice.setText(String.valueOf(obj.getPrice() + "$"));
        labelQuatity.setText(String.valueOf(obj.getQuantity()));
        labelManufacturer.setText(obj.getManufacturer());
        labelSupplier.setText(String.valueOf(obj.getSupplier()));
        labelDescribe.setText(obj.getDescription());

        if(obj instanceof Food){
            Food food = (Food) obj;
            labelType.setText("Food");
            labelExpirationDate.setText(String.valueOf(food.getExpirationDate()));
            labelFlavor.setText(String.valueOf(food.getFlavor()));

            hideScreen();

            gridPaneFood.setManaged(true);
            gridPaneFood.setVisible(true);
        } else if (obj instanceof Toy) {
            Toy toy = (Toy) obj;
            labelType.setText("Toy");
            labelMaterialToy.setText(toy.getMaterial());
            labelSize.setText(String.valueOf(toy.getSize()));

            hideScreen();
            gridPaneToy.setManaged(true);
            gridPaneToy.setVisible(true);
        }
        else if (obj instanceof Cage){
            Cage cage = (Cage) obj;
            labelType.setText("Cage");
            labelMaterialCage.setText(cage.getMaterial());
            labelDimension.setText(cage.getDimension());

            hideScreen();
            gridPaneCage.setManaged(true);
            gridPaneCage.setVisible(true);
        }
        else if (obj instanceof Accessory){
            Accessory accessory = (Accessory) obj;
            labelType.setText("Accessory");
            labelBrand.setText(accessory.getBrand());
            labelTypeAccessory.setText(accessory.getType());

            hideScreen();

            gridPaneAccessory.setManaged(true);
            gridPaneAccessory.setVisible(true);
        }
    }

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hideScreen();

//        scrollPane.prefViewportHeightProperty().bind(borderPane.heightProperty().divide(5));

        scrollPane.viewportBoundsProperty().addListener((obs, oldVal, newVal) -> {
            Node content = scrollPane.getContent();
            if (content != null) {
                double contentHeight = content.prefHeight(-1); // Ước lượng chiều cao của nội dung
                double viewportHeight = newVal.getHeight();

                // So sánh và cập nhật fitToHeight
                scrollPane.setFitToHeight(contentHeight <= viewportHeight);
            }
        });

//        imageViewProduct.setClip(new Circle(10,10,10));


    }
}
