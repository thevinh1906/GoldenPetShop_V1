package com.utc2.petShop.controllers;

import com.utc2.petShop.model.entities.Pet.*;
import com.utc2.petShop.model.entities.User.Admin;
import com.utc2.petShop.model.entities.User.Employee;
import com.utc2.petShop.model.entities.User.User;
import com.utc2.petShop.utils.ImageUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerUser implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane gridPaneCat;

    @FXML
    private GridPane gridPaneGeneral;

    @FXML
    private ImageView imageViewUser;

    @FXML
    private Label labelAddressGeneral;

    @FXML
    private Label labelBirthdayGeneral;

    @FXML
    private Label labelCreationDateGeneral;

    @FXML
    private Label labelEmailGeneral;

    @FXML
    private Label labelGenderGeneral;

    @FXML
    private Label labelIDGeneral;

    @FXML
    private Label labelNameGeneral;

    @FXML
    private Label labelPasswordGeneral;

    @FXML
    private Label labelPhoneNumberGeneral;

    @FXML
    private Label labelPositionEmployee;

    @FXML
    private Label labelSalaryEmployee;

    @FXML
    private Label labelUser;

    @FXML
    private Label labelUsernameGeneral;

    @FXML
    private Label labelWorkingHoursEmployee;

    @FXML
    private Line line;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vBox;

    @FXML
    private HBox hBoxEmployee;

    public void hideScreen() {
        hBoxEmployee.setVisible(false);
        hBoxEmployee.setManaged(false);
    }

    public void receiveData(User obj) {
        user = obj;
        Image image = ImageUtils.cropToImageView(ImageUtils.byteArrayToImage(obj.getImageByte().getImage()),imageViewUser.getFitWidth(),imageViewUser.getFitHeight());
        imageViewUser.setImage(image);
        labelIDGeneral.setText("ID: "+String.valueOf(obj.getId()));
        labelNameGeneral.setText("Name: "+obj.getName());
        if(obj.isGender()){
            labelGenderGeneral.setText("Gender: Male");
        }
        else {
            labelGenderGeneral.setText("Gender: Female");
        }
        labelBirthdayGeneral.setText("Birthday: "+String.valueOf(obj.getBirthDay()));
        labelCreationDateGeneral.setText("Creation Date: "+String.valueOf(obj.getCreationDate()));
        labelUsernameGeneral.setText("Username: "+obj.getUsername());
        labelPasswordGeneral.setText("Password: "+obj.getPassword());
        labelPhoneNumberGeneral.setText("Phone Number: "+obj.getPhoneNumber());
        labelEmailGeneral.setText("Email: "+obj.getEmail());
        labelAddressGeneral.setText("Address: "+obj.getAddress());
        labelUser.setText("User: "+obj.getUsername());

        if(obj instanceof Employee){
            labelSalaryEmployee.setText("Salary: "+String.valueOf(((Employee)obj).getSalary()));
            labelWorkingHoursEmployee.setText("Working: "+String.valueOf(((Employee)obj).getWorkingHours()));
            labelPositionEmployee.setText("Position: "+String.valueOf(((Employee)obj).getPosition()));
            labelUser.setText("Employee");

            hBoxEmployee.setVisible(true);
            hBoxEmployee.setManaged(true);
        }
        if (obj instanceof Admin){
            labelUser.setText("Admin");

            hideScreen();
        }
    }



    private User user = new User();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Platform.runLater(() -> {
            double w = imageViewUser.getFitWidth();
            double h = imageViewUser.getFitHeight();
            double r = Math.min(w, h) / 2;

            Circle clip = new Circle(w / 2, h / 2, r);
            imageViewUser.setClip(clip);
        });


        hideScreen();

        line.endYProperty().bind(vBox.heightProperty());
    }
}
