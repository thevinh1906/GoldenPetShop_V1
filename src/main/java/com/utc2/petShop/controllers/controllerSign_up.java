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

public class controllerSign_up implements Initializable {

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonSignUp;

    @FXML
    private PasswordField textConfirmPassword;

    @FXML
    private PasswordField textPassword;

    @FXML
    private TextField textUsername;

    @FXML
    private Label labelAnnouncement;

    @FXML
    private Label labelPasswordAnnouncement;

    String regex = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$";

    @FXML
    void actionLogin(ActionEvent event) throws IOException {
        scenes.switchScene("sampleSign_in", "Sign in", "applicationSign_In", false);
    }

    @FXML
    void actionSignUp(ActionEvent event) throws IOException {
        if(textPassword.getText().matches(regex)) {
            if (textPassword.getText().equals(textConfirmPassword.getText())) {

                String username;
                username = textUsername.getText().trim();   //lấy dữ liệu username

                String password;
                password = textPassword.getText().trim(); //lấy dữ liệu password

                scenes.switchScene("sampleInsertUser", "Golden Pet Shop", "applicationInsertUser", false);
            } else {
                labelAnnouncement.setVisible(true);
            }
        }
        else {
            labelPasswordAnnouncement.setVisible(true);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelPasswordAnnouncement.setVisible(false);
        labelAnnouncement.setVisible(false);
        buttonSignUp.setDisable(true);
        textUsername.textProperty().addListener((observable, oldValue, newValue) -> updateSignUpButtonState());
        textPassword.textProperty().addListener((observable, oldValue, newValue) -> updateSignUpButtonState());
        textConfirmPassword.textProperty().addListener((observable, oldValue, newValue) -> updateSignUpButtonState());
        Platform.runLater(() -> textUsername.requestFocus());

        textConfirmPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().equals(textPassword.getText().trim())) {
                labelAnnouncement.setVisible(false);
            } else {
                labelAnnouncement.setVisible(true);
            }
        });

        textPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().equals(textConfirmPassword.getText().trim())) {
                labelAnnouncement.setVisible(false);
            } else {
                labelAnnouncement.setVisible(true);
            }
        });


        textPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches(regex)) {
                labelPasswordAnnouncement.setVisible(false);
            }
            else labelPasswordAnnouncement.setVisible(true);
        });

        textUsername.setOnKeyReleased((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                textPassword.requestFocus();
            }
        });
        textPassword.setOnKeyReleased((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                textConfirmPassword.requestFocus();
            }
        });
        textConfirmPassword.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonSignUp.fire();
            }
        });
        Platform.runLater(() -> {
            buttonSignUp.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    buttonSignUp.fire();
                    event.consume();
                }
            });
        });

    }

    public void updateSignUpButtonState() {
        boolean isAnyFieldEmpty = textUsername.getText().trim().isEmpty() || textPassword.getText().trim().isEmpty() || textConfirmPassword.getText().trim().isEmpty();
        buttonSignUp.setDisable(isAnyFieldEmpty);
    }
}
