package com.utc2.petShop.controllers.Admin;

import com.utc2.petShop.model.entities.Pet.*;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.entities.vaccine.Vaccine;
import com.utc2.petShop.model.repository.Insert.InsertPet;
import com.utc2.petShop.model.repository.Select.SelectSupplier;
import com.utc2.petShop.model.repository.Select.SelectVaccine;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class controllerAddPet implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancel;

    @FXML
    private CheckBox checkBoxIndoorCat;

    @FXML
    private CheckBox checkBoxTrainedDog;

    @FXML
    private CheckBox checkBoxVaccinatedGeneral;

    @FXML
    private ChoiceBox<Pet> choiceBoxAnimalGeneral;

    @FXML
    private ComboBox<ECatBreed> comboBoxBreedCat;

    @FXML
    private ComboBox<EDogBreed> comboBoxBreedDog;

    @FXML
    private ComboBox<EHamsterBreed> comboBoxBreedHamster;

    @FXML
    private ComboBox<ERabbitBreed> comboBoxBreedRabbit;

    @FXML
    private ComboBox<Supplier> comboBoxSupplierGeneral;

    @FXML
    private ToggleGroup gender;

    @FXML
    private GridPane gridPaneCat;

    @FXML
    private GridPane gridPaneDog;

    @FXML
    private GridPane gridPaneHamster;

    @FXML
    private GridPane gridPaneRabbit;

    @FXML
    private GridPane gridPaneImage;

    @FXML
    ImageView imageViewPet;

    @FXML
    Label labelDragAnImageHere;

    @FXML
    StackPane stackPaneImage;

    @FXML
    private RadioButton radioButtonFemaleGeneral;

    @FXML
    private RadioButton radioButtonMaleGeneral;

    @FXML
    private Spinner<Integer> spinnerAgeGeneral;

    @FXML
    private TextField texFieldTailLengthHamster;

    @FXML
    private TextArea textAreaDescriptionGeneral;

    @FXML
    private TextField textFieldEarLengthRabbit;

    @FXML
    private TextField textFieldEyeColorCat;

    @FXML
    private TextField textFieldFurColorGeneral;

    @FXML
    private TextField textFieldHealthStatusGeneral;

    @FXML
    private TextField textFieldNameGeneral;

    @FXML
    private TextField textFieldOriginGeneral;

    @FXML
    private TextField textFieldPriceGeneral;

    @FXML
    private TextField textFieldWeightGeneral;

    @FXML
    private ListView<Vaccine> listViewVaccina;

    String priceExceptions = "\\d*(\\.\\d*)?";

    byte[] imageData = null; // dữ liệu nhị phân để lưu

    File file = null;

    @FXML
    void actionAdd(ActionEvent event) {
        String name = textFieldNameGeneral.getText();
        int age = spinnerAgeGeneral.getValue();
        boolean gender = !radioButtonFemaleGeneral.isSelected();
        double price = Double.parseDouble(textFieldPriceGeneral.getText());
        String healthStatus = textFieldHealthStatusGeneral.getText();
        String origin  = textFieldOriginGeneral.getText();
        double weight = Double.parseDouble(textFieldWeightGeneral.getText());
        String furColor = textFieldFurColorGeneral.getText();
        String description = textAreaDescriptionGeneral.getText();
        Supplier supplier = comboBoxSupplierGeneral.getValue();
        String role = String.valueOf(choiceBoxAnimalGeneral.getValue());
        Boolean isIndoor = false;
        String breed = "";
        String eyeColor = "";
        boolean isTrained = false;
        float tailLength = 0;
        float earLength = 0;
        List<Vaccine> vaccines = new ArrayList<>(listViewVaccina.getSelectionModel().getSelectedItems());
        if(role.equals("Cat")){
            breed = String.valueOf(comboBoxBreedCat.getValue());
            eyeColor = textFieldEyeColorCat.getText();
            isIndoor = checkBoxIndoorCat.isSelected();
        }
        else if(role.equals("Dog")){
            breed = String.valueOf(comboBoxBreedDog.getValue());
            isTrained = checkBoxTrainedDog.isSelected();

        }
        else if(role.equals("Hamster")){
            breed = String.valueOf(comboBoxBreedHamster.getValue());
            tailLength = Float.parseFloat(texFieldTailLengthHamster.getText());
        }
        else if(role.equals("Rabbit")){
            breed = String.valueOf(comboBoxBreedRabbit.getValue());
            earLength = Float.parseFloat(textFieldEarLengthRabbit.getText());

        }
        InsertPet.insertPet(imageData, name, age, gender, price, vaccines, healthStatus, origin, weight, furColor, description, supplier, role, isIndoor, breed, eyeColor, isTrained, tailLength, earLength);

        ((Stage) buttonCancel.getScene().getWindow()).close();

    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage)buttonCancel.getScene().getWindow()).close();
    }

    @FXML
    void actionAddImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg", "*.gif", "*.bmp", "*.webp");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(imageViewPet.getScene().getWindow());
        if (file != null) {
            imageViewPet.setImage(new Image(file.toURI().toString()));
            try {
                imageData = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void griPaneVision(){
        gridPaneDog.setVisible(false);
        gridPaneCat.setVisible(false);
        gridPaneHamster.setVisible(false);
        gridPaneRabbit.setVisible(false);
        gridPaneDog.setManaged(false);
        gridPaneCat.setManaged(false);
        gridPaneHamster.setManaged(false);
        gridPaneRabbit.setManaged(false);
    }

    public void insertAnimal(){
        choiceBoxAnimalGeneral.getItems().addAll(new Dog(), new Cat(), new Hamster(), new Rabbit());

        choiceBoxAnimalGeneral.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                if(newValue instanceof Dog){
                    griPaneVision();
                    gridPaneDog.setVisible(true);
                    gridPaneDog.setManaged(true);
                }
                else if(newValue instanceof Cat){
                    griPaneVision();
                    gridPaneCat.setVisible(true);
                    gridPaneCat.setManaged(true);
                }
                else if(newValue instanceof Hamster){
                    griPaneVision();
                    gridPaneHamster.setVisible(true);
                    gridPaneHamster.setManaged(true);
                }
                else if(newValue instanceof Rabbit){
                    griPaneVision();
                    gridPaneRabbit.setVisible(true);
                    gridPaneRabbit.setManaged(true);
                }
            }
            else {
                griPaneVision();
            }
        });
    }

    public void insertAge(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999,1);

        spinnerAgeGeneral.setValueFactory(valueFactory);

        spinnerAgeGeneral.setEditable(true);

        TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), 1,
                change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("\\d*")) { // chỉ cho chữ số (0-9)
                        return change;
                    }
                    return null;
                });
        spinnerAgeGeneral.getEditor().setTextFormatter(formatter);
    }

    public void exceptions(){
        TextFormatter<String> formatterPrice = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.matches("\\d{0,10}(\\.\\d{0,2})?")) {
                return change;
            } else {
                return null;
            }
        });
        textFieldPriceGeneral.setTextFormatter(formatterPrice);
        TextFormatter<String> formatterWeight = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.matches("\\d{0,2}(\\.\\d{0,2})?")) {
                return change;
            } else {
                return null;
            }
        });
        textFieldWeightGeneral.setTextFormatter(formatterWeight);
        TextFormatter<String> formatterTailLengthHamster = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.matches("\\d{0,2}(\\.\\d{0,2})?")) {
                return change;
            } else {
                return null;
            }
        });
        texFieldTailLengthHamster.setTextFormatter(formatterTailLengthHamster);
        TextFormatter<String> formatterEarLengthRabbit = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.matches("\\d{0,2}(\\.\\d{0,2})?")) {
                return change;
            } else {
                return null;
            }
        });
        textFieldEarLengthRabbit.setTextFormatter(formatterEarLengthRabbit);

        imageViewPet.imageProperty().addListener((obs, oldImage, newImage) -> {
            labelDragAnImageHere.setVisible(newImage == null);
        });
    }


    public void buttonAddDisable(){
        String name = textFieldNameGeneral.getText();
        boolean gender = (radioButtonFemaleGeneral.isSelected() || radioButtonMaleGeneral.isSelected());
        String priceText = textFieldPriceGeneral.getText();
        String healthStatus = textFieldHealthStatusGeneral.getText();
        String origin = textFieldOriginGeneral.getText();
        String weightText = textFieldWeightGeneral.getText();
        String furColor = textFieldFurColorGeneral.getText();
        String description = textAreaDescriptionGeneral.getText();
        Supplier supplier = comboBoxSupplierGeneral.getValue();
        Pet pet = choiceBoxAnimalGeneral.getValue();
        String role = String.valueOf(choiceBoxAnimalGeneral.getValue());
        Image image = imageViewPet.getImage();

        boolean isAnyFieldEmpty =
                name.isEmpty() || !gender ||
                        priceText.isEmpty() ||
                        healthStatus.isEmpty() ||
                        origin.isEmpty() ||
                        weightText.isEmpty() ||
                        furColor.isEmpty() ||
                        description.isEmpty() ||
                        supplier == null ||
                        image == null ||
                        role.equals("null");

        // Kiểm tra các trường riêng theo từng loại động vật
        if (pet instanceof Cat) {
            isAnyFieldEmpty |=
                    comboBoxBreedCat.getValue() == null ||
                            textFieldEyeColorCat.getText().isEmpty();
        } else if (pet instanceof Dog) {
            isAnyFieldEmpty |=
                    comboBoxBreedDog.getValue() == null;
        } else if (pet instanceof Hamster) {
            isAnyFieldEmpty |=
                    comboBoxBreedHamster.getValue() == null ||
                            texFieldTailLengthHamster.getText().isEmpty();
        } else if (pet instanceof Rabbit) {
            isAnyFieldEmpty |=
                    comboBoxBreedRabbit.getValue() == null ||
                            textFieldEarLengthRabbit.getText().isEmpty();
        }

        buttonAdd.setDisable(isAnyFieldEmpty);
    }

    public void setButtonAddDisable(){
        textFieldNameGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        gender.selectedToggleProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldPriceGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldHealthStatusGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldOriginGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldWeightGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldFurColorGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textAreaDescriptionGeneral.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        comboBoxSupplierGeneral.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        choiceBoxAnimalGeneral.valueProperty().addListener((obs, oldVal, newVal) -> {
//            updateAnimalSpecificFields(); // nếu bạn thay đổi loại thú, cần gọi lại logic hiển thị trường phù hợp
            buttonAddDisable();
        });

        // Các trường riêng theo loại thú cưng
        comboBoxBreedCat.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldEyeColorCat.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        comboBoxBreedDog.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        comboBoxBreedHamster.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        texFieldTailLengthHamster.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        comboBoxBreedRabbit.valueProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        textFieldEarLengthRabbit.textProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
        imageViewPet.imageProperty().addListener((obs, oldVal, newVal) -> buttonAddDisable());
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

        jumpOnEnter(textFieldNameGeneral,spinnerAgeGeneral);
        jumpOnEnter(spinnerAgeGeneral,textFieldPriceGeneral);
        jumpOnEnter(textFieldPriceGeneral,textFieldHealthStatusGeneral);
        jumpOnEnter(textFieldHealthStatusGeneral,textFieldOriginGeneral);
        jumpOnEnter(textFieldOriginGeneral,textFieldWeightGeneral);
        jumpOnEnter(textFieldWeightGeneral,textFieldFurColorGeneral);
        jumpOnEnter(textFieldFurColorGeneral,comboBoxSupplierGeneral);
        jumpOnEnter(comboBoxSupplierGeneral,textAreaDescriptionGeneral);
        jumpOnEnter(textAreaDescriptionGeneral,choiceBoxAnimalGeneral);

        choiceBoxAnimalGeneral.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal instanceof Cat) {
                jumpOnEnter(choiceBoxAnimalGeneral,comboBoxBreedCat);
                jumpOnEnter(comboBoxBreedCat,textFieldEyeColorCat);
            }
            else if (newVal instanceof Dog) {
                jumpOnEnter(choiceBoxAnimalGeneral,comboBoxBreedDog);
            }
            else if (newVal instanceof Hamster) {
                jumpOnEnter(choiceBoxAnimalGeneral,comboBoxBreedHamster);
                jumpOnEnter(comboBoxBreedHamster,texFieldTailLengthHamster);
            }
            else if (newVal instanceof Rabbit) {
                jumpOnEnter(choiceBoxAnimalGeneral,comboBoxBreedRabbit);
                jumpOnEnter(comboBoxBreedRabbit,textFieldEarLengthRabbit);
            }
        });

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

    private static ObservableList<Supplier> listSupplier;

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
                    file = db.getFiles().get(0);
                    imageViewPet.setImage(new Image(file.toURI().toString())); // hiển thị
                    imageData = Files.readAllBytes(file.toPath());
                    success = true;

                } else {
                    // 🌐 Ảnh từ web
                    String url = db.hasUrl() ? db.getUrl() : db.getString();
                    System.out.println("Dropped URL/String: " + url);

                    String imageUrl = null;
                    Pattern pattern = Pattern.compile("mediaurl=([^&]+)");
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

                            imageData = baos.toByteArray();
                            imageViewPet.setImage(new Image(new ByteArrayInputStream(imageData))); // hiển thị
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



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listSupplier = FXCollections.observableArrayList(SelectSupplier.getAllSuppliers());

        griPaneVision();

        insertAnimal();

        insertAge();

        exceptions();

        comboBoxSupplierGeneral.setItems(listSupplier);

        comboBoxBreedDog.getItems().addAll(EDogBreed.values());

        comboBoxBreedCat.getItems().addAll(ECatBreed.values());

        comboBoxBreedHamster.getItems().addAll(EHamsterBreed.values());

        comboBoxBreedRabbit.getItems().addAll(ERabbitBreed.values());

        setButtonAddDisable();

        buttonEnter();

        dragAndDropTheImage();

        listViewVaccina.setItems(
                FXCollections.observableArrayList(
                        SelectVaccine.getAllVaccines()
                )
        );

        listViewVaccina.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}
