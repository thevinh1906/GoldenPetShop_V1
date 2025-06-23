package com.utc2.petShop.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import com.utc2.petShop.model.services.scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controllerSign_in implements Initializable {

    @FXML
    private Button buttonCreate;

    @FXML
    private Button buttonSignIn;

    @FXML
    private PasswordField textPassword;

    @FXML
    private TextField textUsername;

    @FXML
    private Label labelAnnouncement;

    public void actionSignIn() throws IOException {
        if (textUsername.getText().equals(/*bỏ user name vào đây*/"username") && textPassword.getText().equals(/*bỏ password vào đây*/"password")) {
            scenes.switchScene("sampleProgressBarEmployee", "Golden Pet Shop","applicationProgressBarEmployee", false);
        } else if (textUsername.getText().equals(/*bỏ user name vào đây*/"usernameAdmin") && textPassword.getText().equals(/*bỏ password vào đây*/"passwordAdmin")) {
            scenes.switchSceneNotTitleBar("Admin/sampleProgressBarAdmin", "Golden Pet Shop","Admin/applicationProgressBarAdmin", false);
        } else {
            textPassword.clear();
            labelAnnouncement.setVisible(true);
        }
    }

    public void actionCreate(ActionEvent event) throws IOException {
        scenes.switchScene("sampleSign_up","Sign up", "applicationSign_up",false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelAnnouncement.setVisible(false);
        buttonSignIn.setDisable(true);
        textUsername.textProperty().addListener((observable, oldValue, newValue) -> updateSignInButtonState());
        textPassword.textProperty().addListener((observable, oldValue, newValue) -> updateSignInButtonState());

        textUsername.setOnKeyReleased((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                textPassword.requestFocus();
            }
        });
        textPassword.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonSignIn.fire();
            }
        });

        Platform.runLater(() -> {
            buttonSignIn.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    buttonSignIn.fire();
                    event.consume();
                }
            });
        });
    }
    public void updateSignInButtonState() {
        boolean isAnyFieldEmpty = textUsername.getText().trim().isEmpty() || textPassword.getText().trim().isEmpty();
        buttonSignIn.setDisable(isAnyFieldEmpty);
    }
}
