package com.utc2.petShop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerHome implements Initializable {

    @FXML
    private TreeView<String> tree;

    @FXML
    private ScrollPane SrcollLeft,ScrollCenter;

    @FXML
    private Button buttonFind;

    @FXML
    private Button buttonSetting;

    @FXML
    private Button buttonUser;

    @FXML
    private TextField textSreach;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonForward;

    public void actionFind(ActionEvent event) {
        System.out.println(textSreach.getText());
        //xử lý sự kiện tìm kiếm
    }

    public void actionSetting(ActionEvent event) {
        //xử lý sự kiện cài đặt
    }

    public void actionUser(ActionEvent event) {
        //xử lý sự kiện user
    }

    public void actionBack(ActionEvent event) {
        //xử lý sự kiên back
    }

    public void actionForward(ActionEvent event) {
        //sử lý sự kiện Forward
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> root = new TreeItem<String>();
        TreeItem<String> user = new TreeItem<String>("User");
        TreeItem<String> pet = new TreeItem<String>("Pet");
        TreeItem<String> dog = new TreeItem<String>("Dog");
        TreeItem<String> cat = new TreeItem<String>("Cat");
        TreeItem<String> hamster = new TreeItem<String>("Hamster");
        TreeItem<String> rabbit = new TreeItem<String>("Rabbit");
        pet.getChildren().addAll(dog, cat, hamster, rabbit);
        TreeItem<String> product = new TreeItem<String>("Product");
        TreeItem<String> food = new TreeItem<String>("Food");
        TreeItem<String> toy = new TreeItem<String>("Toy");
        TreeItem<String> cage = new TreeItem<String>("Cage");
        TreeItem<String> accessory = new TreeItem<String>("Accessory");
        product.getChildren().addAll(food, toy, cage, accessory);
        TreeItem<String> transaction = new TreeItem<String>("transaction");
        root.getChildren().addAll(user, pet, product, transaction);
        tree.setRoot(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        // rằng buộc cho TreeView
        });
    }
}
