package com.utc2.petShop.controllers.Admin;

import com.utc2.petShop.model.entities.Image.ImageByte;
import com.utc2.petShop.model.entities.User.Admin;
import com.utc2.petShop.model.entities.User.EEmployeePosition;
import com.utc2.petShop.model.entities.User.Employee;
import com.utc2.petShop.model.entities.User.User;
import com.utc2.petShop.model.repository.UpdateById.UpdateUser;
import com.utc2.petShop.utils.ImageUtils;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class controllerEditUser implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancel;

    @FXML
    private ChoiceBox<EEmployeePosition> choiceBoxPositionGeneral;

    @FXML
    private DatePicker datePickerBirthDateGeneral;

    @FXML
    private DatePicker datePickerCreationDateGeneral;

    @FXML
    private ToggleGroup gender;

    @FXML
    private GridPane gridPaneGeneral;

    @FXML
    private RadioButton radioButtonFemaleGeneral;

    @FXML
    private RadioButton radioButtonMaleGeneral;

    @FXML
    private TextField textFieldAddressGeneral;

    @FXML
    private TextField textFieldEmailGeneral;

    @FXML
    private TextField textFieldNameGeneral;

    @FXML
    private TextField textFieldPasswordGeneral;

    @FXML
    private TextField textFieldPhoneNumberGeneral;

    @FXML
    private TextField textFieldSalaryGeneral;

    @FXML
    private TextField textFieldUsernameGeneral;

    @FXML
    private TextField textFieldWorkingHoursGeneral;

    @FXML
    private ImageView imageViewUser;

    @FXML
    private Label labelDragAnImageHere;

    @FXML
    private StackPane stackPaneImage;

    private ImageByte imageByte;

    @FXML
    void actionAdd(ActionEvent event) {
        String username = textFieldUsernameGeneral.getText();
        String password = textFieldPasswordGeneral.getText();
        String name = textFieldNameGeneral.getText();
        boolean gender = !radioButtonFemaleGeneral.isSelected();
        String email = textFieldEmailGeneral.getText();
        String phoneNumber = textFieldPhoneNumberGeneral.getText();
        String address = textFieldAddressGeneral.getText();
        LocalDate birthDay = datePickerBirthDateGeneral.getValue();
        LocalDate creationDate = datePickerCreationDateGeneral.getValue();
        String position = String.valueOf(choiceBoxPositionGeneral.getValue());
        System.out.println(position);
        float salary = 0;
        String workingHours = textFieldWorkingHoursGeneral.getText();
        String role = "";
        if(!position.equals("null")){
            role = "Employee";
            salary = Float.parseFloat(textFieldSalaryGeneral.getText());
        }

        UpdateUser.updateUser(user.getId(),username,password,name,gender,email,phoneNumber,address,birthDay,creationDate,position,salary,workingHours,role,imageByte);

        ((Stage) buttonCancel.getScene().getWindow()).close();

    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage)buttonCancel.getScene().getWindow()).close();
    }

    @FXML
    void actionChangeImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg", "*.gif", "*.bmp", "*.webp");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (file != null) {
            try {
                imageByte.setImage(Files.readAllBytes(file.toPath()));
                Image image = ImageUtils.cropToImageView(ImageUtils.byteArrayToImage(imageByte.getImage()),imageViewUser.getFitWidth(),imageViewUser.getFitHeight());
                imageViewUser.setImage(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void exceptions() {
        TextFormatter<String> formatterPhone = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            if (newText.isEmpty() || newText.matches("0\\d{0,9}")) {
                return change;
            } else {
                return null;
            }
        });
        textFieldPhoneNumberGeneral.setTextFormatter(formatterPhone);

        textFieldPhoneNumberGeneral.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 10) {
                textFieldPhoneNumberGeneral.setStyle("-fx-border-color: red;");
                buttonAddDisable();
            } else {
                textFieldPhoneNumberGeneral.setStyle(""); // xóa viền đỏ nếu hợp lệ
            }
        });

        TextFormatter<String> formatterEmail = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            if (newText.isEmpty() || newText.matches("[a-zA-Z0-9@._\\-]*")) {
                return change;
            } else {
                return null;
            }
        });
        textFieldEmailGeneral.setTextFormatter(formatterEmail);

        textFieldEmailGeneral.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
                textFieldEmailGeneral.setStyle("-fx-border-color: red;");
                buttonAddDisable();
            }
            else {
                textFieldEmailGeneral.setStyle("");
            }
        });

        textFieldEmailGeneral.setTextFormatter(formatterEmail);

        TextFormatter<String> formatterSalary = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.matches("\\d*(\\.\\d{0,2})?")) {
                return change;
            } else {
                return null;
            }
        });

        textFieldSalaryGeneral.setTextFormatter(formatterSalary);

        datePickerBirthDateGeneral.setValue(datePickerCreationDateGeneral.getValue().minusYears(18));
        datePickerBirthDateGeneral.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (!empty && date.isAfter(datePickerCreationDateGeneral.getValue().minusYears(18))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // đỏ nhạt cho ngày không hợp lệ
                }
            }
        });

        textFieldSalaryGeneral.setDisable(true);
        textFieldWorkingHoursGeneral.setDisable(true);

        choiceBoxPositionGeneral.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textFieldSalaryGeneral.setDisable(false);
                textFieldWorkingHoursGeneral.setDisable(false);
            }
        });

        labelDragAnImageHere.visibleProperty().bind(
                imageViewUser.imageProperty().isNull()
        );

    }

    public void buttonAddDisable() {
        boolean isAnyFieldEmpty =
                textFieldUsernameGeneral.getText().isEmpty() ||
                        textFieldPasswordGeneral.getText().isEmpty() ||
                        textFieldNameGeneral.getText().isEmpty() ||
                        !(radioButtonFemaleGeneral.isSelected() || radioButtonMaleGeneral.isSelected()) ||
                        textFieldEmailGeneral.getText().isEmpty() || (textFieldPhoneNumberGeneral.getText().length() < 10) ||
                        textFieldPhoneNumberGeneral.getText().isEmpty() || !textFieldEmailGeneral.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") ||
                        textFieldAddressGeneral.getText().isEmpty() || datePickerBirthDateGeneral.getValue().isAfter(datePickerCreationDateGeneral.getValue().minusYears(18)) ||
                        datePickerBirthDateGeneral.getValue() == null || imageViewUser.getImage() == null ||
                        datePickerCreationDateGeneral.getValue() == null;

        if(!(choiceBoxPositionGeneral.getValue() == null)){
            isAnyFieldEmpty |= textFieldSalaryGeneral.getText().isEmpty() ||
                    textFieldWorkingHoursGeneral.getText().isEmpty();
        }



        buttonAdd.setDisable(isAnyFieldEmpty);
    }

    public void setButtonAddDisable() {
        buttonAdd.setDisable(true);
        ChangeListener<String> listener = (observable, oldValue, newValue) -> buttonAddDisable();

        textFieldUsernameGeneral.textProperty().addListener(listener);
        textFieldPasswordGeneral.textProperty().addListener(listener);
        textFieldNameGeneral.textProperty().addListener(listener);
        gender.selectedToggleProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldEmailGeneral.textProperty().addListener(listener);
        textFieldPhoneNumberGeneral.textProperty().addListener(listener);
        textFieldAddressGeneral.textProperty().addListener(listener);
        textFieldSalaryGeneral.textProperty().addListener(listener);
        textFieldWorkingHoursGeneral.textProperty().addListener(listener);

        // Với DatePicker và ChoiceBox, dùng valueProperty()
        datePickerBirthDateGeneral.valueProperty().addListener((obs, oldDate, newDate) -> buttonAddDisable());
        datePickerCreationDateGeneral.valueProperty().addListener((obs, oldDate, newDate) -> buttonAddDisable());
        choiceBoxPositionGeneral.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        imageViewUser.imageProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());

    }

    public void receiveData(User obj){
        user = obj;
        Image image = ImageUtils.cropToImageView(ImageUtils.byteArrayToImage(obj.getImageByte().getImage()),imageViewUser.getFitWidth(),imageViewUser.getFitHeight());
        imageByte = obj.getImageByte();
        imageViewUser.setImage(image);
        textFieldUsernameGeneral.setText(obj.getUsername());
        textFieldPasswordGeneral.setText(obj.getPassword());
        textFieldNameGeneral.setText(obj.getName());
        if(obj.isGender()){
            radioButtonMaleGeneral.setSelected(true);
        }
        else {
            radioButtonFemaleGeneral.setSelected(true);
        }
        textFieldEmailGeneral.setText(obj.getEmail());
        textFieldPhoneNumberGeneral.setText(obj.getPhoneNumber());
        textFieldAddressGeneral.setText(obj.getAddress());
        datePickerBirthDateGeneral.setValue(obj.getBirthDay());
        datePickerCreationDateGeneral.setValue(obj.getCreationDate());
        if (obj instanceof Employee){
            Employee employee = (Employee) obj;
            choiceBoxPositionGeneral.setValue(employee.getPosition());
            textFieldSalaryGeneral.setText(String.valueOf(employee.getSalary()));
            textFieldWorkingHoursGeneral.setText(String.valueOf(employee.getWorkingHours()));
        }
        else if (obj instanceof Admin){
            Admin admin = (Admin) obj;
        }

    }

    private void jumpOnEnter(Control current, Control next) {
        current.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Trường hợp đặc biệt với TextArea: Enter là xuống dòng
                if (!(current instanceof TextArea)) {
                    next.requestFocus();
                }
            }
        });
    }

    public void buttonEnter() {

        jumpOnEnter(textFieldUsernameGeneral, textFieldPasswordGeneral);
        jumpOnEnter(textFieldPasswordGeneral, textFieldNameGeneral);
        jumpOnEnter(textFieldNameGeneral, textFieldEmailGeneral);
        jumpOnEnter(textFieldEmailGeneral, textFieldPhoneNumberGeneral);
        jumpOnEnter(textFieldPhoneNumberGeneral, textFieldAddressGeneral);
        jumpOnEnter(textFieldAddressGeneral,datePickerBirthDateGeneral);
        jumpOnEnter(datePickerBirthDateGeneral,datePickerCreationDateGeneral);
        jumpOnEnter(datePickerCreationDateGeneral,choiceBoxPositionGeneral);
        jumpOnEnter(choiceBoxPositionGeneral,textFieldSalaryGeneral);
        jumpOnEnter(textFieldSalaryGeneral,textFieldWorkingHoursGeneral);

        Platform.runLater(() -> {
            Scene scene = root.getScene();
            if (scene != null) {
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        buttonAdd.fire();
                    }
                });
            }
        });
    }

    public void dragAndDropTheImage(){
        stackPaneImage.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles() || db.hasUrl() || db.hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        stackPaneImage.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;



            try {
                if (db.hasFiles()) {
                    // 📁 Ảnh từ máy
                    File file = db.getFiles().get(0);
                    imageByte.setImage(Files.readAllBytes(file.toPath()));
                    Image image = ImageUtils.cropToImageView(ImageUtils.byteArrayToImage(imageByte.getImage()),imageViewUser.getFitWidth(),imageViewUser.getFitHeight());
                    imageViewUser.setImage(image);
                    success = true;

                } else {
                    // 🌐 Ảnh từ web
                    String url = db.hasUrl() ? db.getUrl() : db.getString();
                    System.out.println("Dropped URL/String: " + url);

                    String imageUrl = null;
                    Pattern pattern = Pattern.compile("(?:mediaurl|imgurl)=([^&]+)");
                    Matcher matcher = pattern.matcher(url);
                    if (matcher.find()) {
                        imageUrl = URLDecoder.decode(matcher.group(1), StandardCharsets.UTF_8);
                    } else if (url.matches(".*\\.(jpg|jpeg|png|gif|bmp|webp).*")) {
                        imageUrl = url;
                    }

                    System.out.println("Final imageUrl: " + imageUrl);

                    if (imageUrl != null) {
                        // Đọc InputStream từ web và lưu về byte[]
                        URL imageURL = new URL(imageUrl);
                        String origin = imageURL.getProtocol() + "://" + imageURL.getHost() + "/";
                        HttpURLConnection conn = (HttpURLConnection) imageURL.openConnection();
                        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                        conn.setRequestProperty("Referer", origin);
                        conn.setRequestProperty("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");
                        conn.connect();

                        try (InputStream is = conn.getInputStream();
                             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

                            byte[] buffer = new byte[4096];
                            int bytesRead;
                            while ((bytesRead = is.read(buffer)) != -1) {
                                baos.write(buffer, 0, bytesRead);
                            }

                            imageByte.setImage(baos.toByteArray());
                            Image image = ImageUtils.byteArrayToImage(imageByte.getImage());
                            System.out.printf("Loaded image: error=%b, w=%.0f, h=%.0f%n",
                                    image.isError(), image.getWidth(), image.getHeight());
                            imageViewUser.setImage(ImageUtils.cropToImageView(image,150,150));
                            success = true;
                        }
                    }
                }

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Không thể tải ảnh");
                alert.setContentText("Server ảnh đã chặn quyền truy cập. Hãy dùng ảnh từ máy hoặc trang khác.");
                alert.showAndWait();
                e.printStackTrace();
            }

            event.setDropCompleted(success);
            event.consume();
        });

    }

    private static User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        datePickerCreationDateGeneral.setValue(LocalDate.now());

        exceptions();

        choiceBoxPositionGeneral.getItems().addAll(EEmployeePosition.values());

        setButtonAddDisable();

        buttonEnter();

        dragAndDropTheImage();

    }
}
