package com.utc2.petShop.controllers.Admin;

import com.utc2.petShop.model.entities.Image.ImageByte;
import com.utc2.petShop.model.entities.Product.*;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.repository.Select.SelectSupplier;
import com.utc2.petShop.model.repository.UpdateById.UpdateProduct;
import com.utc2.petShop.utils.ImageUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class controllerEditProduct implements Initializable {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancel;

    @FXML
    private ChoiceBox<Product> choiceBoxPetSuppliesGeneral;

    @FXML
    private ComboBox<Supplier> comboBoxSupplierGeneral;

    @FXML
    private DatePicker datePickerExpirationDateFood;

    @FXML
    private GridPane gridPaneAccessory;

    @FXML
    private GridPane gridPaneCage;

    @FXML
    private GridPane gridPaneFood;

    @FXML
    private GridPane gridPaneGeneral;

    @FXML
    private GridPane gridPaneImage;

    @FXML
    private GridPane gridPaneToy;

    @FXML
    private HBox hBoxImageView;

    @FXML
    private Label labelDragAnImageHere;

    @FXML
    private BorderPane root;

    @FXML
    private Spinner<Integer> spinnerQuantityGeneral;

    @FXML
    private StackPane stackPaneImage;

    @FXML
    private TextArea textAreaDescriptionGeneral;

    @FXML
    private TextField textFieldBrandAccessory;

    @FXML
    private TextField textFieldDimensionCage;

    @FXML
    private TextField textFieldFlavorFood;

    @FXML
    private TextField textFieldManufacturerGeneral;

    @FXML
    private TextField textFieldMaterialCage;

    @FXML
    private TextField textFieldMaterialToy;

    @FXML
    private TextField textFieldNameGeneral;

    @FXML
    private TextField textFieldPriceGeneral;

    @FXML
    private TextField textFieldSizeToy;

    @FXML
    private TextField textFieldTypeAccessory;

    List<ImageByte> imageBytes = new ArrayList<>();

    List<byte[]> imageData = new ArrayList<>();

    @FXML
    void actionAdd(ActionEvent event) {
        Supplier supplier = comboBoxSupplierGeneral.getValue();
        String name = textFieldNameGeneral.getText();
        double price = Double.parseDouble(textFieldPriceGeneral.getText());
        int quantity = spinnerQuantityGeneral.getValue();
        String description = textAreaDescriptionGeneral.getText();
        String manufacturer = textFieldManufacturerGeneral.getText();
        String role = String.valueOf(choiceBoxPetSuppliesGeneral.getValue());
        String type = "";
        String brand = "";
        LocalDate expirationDate = null;
        String flavor = "";
        String dimension = "";
        String material = "";
        String size = "";
        if(role.equals("Food")){
            expirationDate = datePickerExpirationDateFood.getValue();
            flavor = textFieldFlavorFood.getText();
        }
        else if(role.equals("Toy")){
            material = textFieldMaterialToy.getText();
            size = textFieldSizeToy.getText();
        }
        else if(role.equals("Cage")){
            material = textFieldMaterialCage.getText();
            dimension = textFieldDimensionCage.getText();
        }
        else if(role.equals("Accessory")){
            brand = textFieldBrandAccessory.getText();
            type = textFieldTypeAccessory.getText();
        }

        UpdateProduct.updateProduct(product.getId(),supplier,name,price,quantity,description,manufacturer,type,brand,expirationDate,flavor,dimension,material,size,role,imageBytes);

        ((Stage) buttonCancel.getScene().getWindow()).close();

    }

    @FXML
    void actionCancel(ActionEvent event) {
        ((Stage)buttonCancel.getScene().getWindow()).close();
    }

    @FXML
    void actionAddImage(ActionEvent event) {
        if (imageData.size() < 5) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg", "*.gif", "*.bmp", "*.webp");
            fileChooser.getExtensionFilters().add(extFilter);
            List<File> files = fileChooser.showOpenMultipleDialog(root.getScene().getWindow());

            if (files != null) {
                int count = 0;
                for (ImageByte imageByte : imageBytes) {
                    if (imageByte.getImage() == null) {
                        count++;
                    }
                }
                int remainingSlot = 5 - imageBytes.size() + count;

                List<byte[]> fileByte = new ArrayList<>();
                for (File file : files) {
                    try {
                        fileByte.add(Files.readAllBytes(file.toPath()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
//                }
                if (files.size() > remainingSlot) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Chỉ thêm được " + remainingSlot + " ảnh nữa.");
                    alert.showAndWait();
                } else {
                    refreshImagesOnHBox(fileByte);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Chỉ được chọn tối đa 5 ảnh!");
            alert.showAndWait();
        }
    }

    public void griPaneVision(){
        gridPaneToy.setVisible(false);
        gridPaneCage.setVisible(false);
        gridPaneFood.setVisible(false);
        gridPaneAccessory.setVisible(false);
        gridPaneToy.setManaged(false);
        gridPaneCage.setManaged(false);
        gridPaneFood.setManaged(false);
        gridPaneAccessory.setManaged(false);
    }

    public void insertPetSupplies() {
        choiceBoxPetSuppliesGeneral.getItems().addAll(new Food(), new Toy(), new Cage(), new Accessory());

        choiceBoxPetSuppliesGeneral.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof Food) {
                griPaneVision();
                gridPaneFood.setVisible(true);
                gridPaneFood.setManaged(true);
            }
            else if (newValue instanceof Toy) {
                griPaneVision();
                gridPaneToy.setVisible(true);
                gridPaneToy.setManaged(true);
            }else if (newValue instanceof Cage) {
                griPaneVision();
                gridPaneCage.setVisible(true);
                gridPaneCage.setManaged(true);
            }else if (newValue instanceof Accessory) {
                griPaneVision();
                gridPaneAccessory.setVisible(true);
                gridPaneAccessory.setManaged(true);
            }
            else {
                griPaneVision();
            }
        });
    }

    public void insertQuantity(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000,1);

        spinnerQuantityGeneral.setValueFactory(valueFactory);

        spinnerQuantityGeneral.setEditable(true);

        TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), 1,
                change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("\\d*")) { // chỉ cho chữ số (0-9)
                        return change;
                    }
                    return null;
                });
        spinnerQuantityGeneral.getEditor().setTextFormatter(formatter);
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

        datePickerExpirationDateFood.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (!empty && !date.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // màu hồng nhạt cho ngày bị vô hiệu hóa
                }
            }
        });
        TextFormatter<String> formatterDimensionCage = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.isEmpty() || newText.matches("\\d{0,4}(x\\d{0,4}){0,2}")) {
                return change;
            }
            return null;
        });
        textFieldDimensionCage.setTextFormatter(formatterDimensionCage);

        textFieldDimensionCage.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{1,4}x\\d{1,4}x\\d{1,4}")) {
                textFieldDimensionCage.setStyle("-fx-border-color: red;");
                buttonAddDisable();
            } else {
                textFieldDimensionCage.setStyle(""); // Hợp lệ -> xóa border đỏ
            }
        });


        TextFormatter<String> formatterSizeToy = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            // Chỉ cho số thực dương: có hoặc không có phần thập phân
            if (newText.isEmpty() || newText.matches("\\d{0,4}(x\\d{0,4}){0,2}")) {
                return change;
            }
            return null;
        });
        textFieldSizeToy.setTextFormatter(formatterSizeToy);

        textFieldSizeToy.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{1,4}x\\d{1,4}x\\d{1,4}")) {
                textFieldSizeToy.setStyle("-fx-border-color: red;");
                buttonAddDisable();
            } else {
                textFieldSizeToy.setStyle(""); // Hợp lệ -> xóa border đỏ
            }
        });

        hBoxImageView.getChildren().addListener((ListChangeListener<Node>) change -> {
            labelDragAnImageHere.setVisible(hBoxImageView.getChildren().isEmpty());
        });
    }

    public void buttonAddDisable() {
        boolean isAnyFieldEmpty =
                comboBoxSupplierGeneral.getValue() == null ||
                        textFieldNameGeneral.getText().isEmpty() ||
                        textFieldPriceGeneral.getText().isEmpty() ||
                        textAreaDescriptionGeneral.getText().isEmpty() ||
                        textFieldManufacturerGeneral.getText().isEmpty() ||
                        hBoxImageView.getChildren().isEmpty() ||
                        choiceBoxPetSuppliesGeneral.getValue() == null;

        String role = String.valueOf(choiceBoxPetSuppliesGeneral.getValue());

        switch (role) {
            case "Food" -> {
                isAnyFieldEmpty |=
                        datePickerExpirationDateFood.getValue() == null ||
                                textFieldFlavorFood.getText().isEmpty();
            }
            case "Toy" -> {
                isAnyFieldEmpty |=
                        textFieldMaterialToy.getText().isEmpty() ||
                                textFieldSizeToy.getText().isEmpty()  || !textFieldSizeToy.getText().matches("\\d{1,4}x\\d{1,4}x\\d{1,4}");
            }
            case "Cage" -> {
                isAnyFieldEmpty |=
                        textFieldMaterialCage.getText().isEmpty() ||
                                textFieldDimensionCage.getText().isEmpty() || !textFieldDimensionCage.getText().matches("\\d{1,4}x\\d{1,4}x\\d{1,4}");
            }
            case "Accessory" -> {
                isAnyFieldEmpty |=
                        textFieldBrandAccessory.getText().isEmpty() ||
                                textFieldTypeAccessory.getText().isEmpty();
            }
        }

        buttonAdd.setDisable(isAnyFieldEmpty);
    }

    public void setButtonAddDisable() {
        buttonAdd.setDisable(true);

        // Gắn listener cho các trường chính
        textFieldNameGeneral.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        textFieldPriceGeneral.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        textAreaDescriptionGeneral.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        textFieldManufacturerGeneral.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        comboBoxSupplierGeneral.valueProperty().addListener((obs, o, n) -> buttonAddDisable());
        choiceBoxPetSuppliesGeneral.valueProperty().addListener((obs, o, n) -> buttonAddDisable());

        // Các trường theo từng loại (bạn nên kiểm tra role để chọn listener cần gắn)
        textFieldFlavorFood.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        datePickerExpirationDateFood.valueProperty().addListener((obs, o, n) -> buttonAddDisable());

        textFieldMaterialCage.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        textFieldDimensionCage.textProperty().addListener((obs, o, n) -> buttonAddDisable());

        textFieldSizeToy.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        textFieldMaterialToy.textProperty().addListener((obs, o, n) -> buttonAddDisable());

        textFieldBrandAccessory.textProperty().addListener((obs, o, n) -> buttonAddDisable());
        textFieldTypeAccessory.textProperty().addListener((obs, o, n) -> buttonAddDisable());

        hBoxImageView.getChildren().addListener((ListChangeListener<Node>) change -> buttonAddDisable());

        // Kiểm tra lần đầu khi giao diện hiển thị
        buttonAddDisable();
    }

    private void refreshImagesOnHBox(List<byte[]> images) {
        hBoxImageView.getChildren().clear();
        imageData.clear();

        if (images != null) {
            int count = 0;
            for (ImageByte imageByte : imageBytes) {
                if (imageByte.getImage() == null) {
                    count++;
                }
            }

            for (ImageByte imageByte : imageBytes) {
                if (imageByte.getImage() == null && !images.isEmpty()) {
                    imageByte.setImage(images.remove(0));
                } else if (images.isEmpty()) {
                    break;
                }
            }
            int imageAdd = 5 - imageBytes.size();
            int toAdd = Math.min(imageAdd, images.size());
            for (int i = 0; i < toAdd; i++) {
                    imageBytes.add(new ImageByte(0,images.get(i)));
            }

        }

        for (ImageByte image : imageBytes) {
            if(image.getImage()!=null) {
                imageData.add(image.getImage());
                Image img = ImageUtils.cropToImageView(ImageUtils.byteArrayToImage(image.getImage()),50,50);
//            images.add(img);
                Button button = new Button("x");
                button.getStyleClass().addAll("button1", "delete-button");

                button.setOnAction(event -> {
                    image.setImage(null);
                    refreshImagesOnHBox(new ArrayList<>());
                });

                ImageView imageView = new ImageView(img);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setPreserveRatio(true);

                StackPane stackPane = new StackPane(imageView,button);
                stackPane.setAlignment(button, Pos.TOP_RIGHT);
                StackPane.setMargin(imageView, new Insets(5));


                hBoxImageView.getChildren().add(stackPane);
            }
        }
    }

    public void receiveData(Product obj){
        product = obj;
        imageBytes = obj.getImages();
        refreshImagesOnHBox(new ArrayList<>());
        textFieldNameGeneral.setText(obj.getName());
        spinnerQuantityGeneral.getValueFactory().setValue(obj.getQuantity());
        textFieldPriceGeneral.setText(String.valueOf(obj.getPrice()));
        comboBoxSupplierGeneral.setValue(obj.getSupplier());
        textAreaDescriptionGeneral.setText(obj.getDescription());
        textFieldManufacturerGeneral.setText(obj.getManufacturer());
        if(obj instanceof Food){
            Food food = (Food) obj;
            choiceBoxPetSuppliesGeneral.setValue(food);
            datePickerExpirationDateFood.setValue(food.getExpirationDate());
            textFieldFlavorFood.setText(food.getFlavor());
        }
        else if(obj instanceof Toy){
            Toy toy = (Toy) obj;
            choiceBoxPetSuppliesGeneral.setValue(toy);
            textFieldMaterialToy.setText(toy.getMaterial());
            textFieldSizeToy.setText(toy.getSize());
        }
        else if(obj instanceof Cage){
            Cage cage = (Cage) obj;
            choiceBoxPetSuppliesGeneral.setValue(cage);
            textFieldMaterialCage.setText(cage.getMaterial());
            textFieldDimensionCage.setText(cage.getDimension());
        }
        else if(obj instanceof Accessory){
            Accessory accessory = (Accessory) obj;
            choiceBoxPetSuppliesGeneral.setValue(accessory);
            textFieldTypeAccessory.setText(accessory.getType());
            textFieldBrandAccessory.setText(accessory.getBrand());
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

        jumpOnEnter(textFieldNameGeneral,spinnerQuantityGeneral);
        jumpOnEnter(spinnerQuantityGeneral,textFieldPriceGeneral);
        jumpOnEnter(textFieldPriceGeneral,comboBoxSupplierGeneral);
        jumpOnEnter(choiceBoxPetSuppliesGeneral,textAreaDescriptionGeneral);
        jumpOnEnter(textFieldManufacturerGeneral,choiceBoxPetSuppliesGeneral);

        choiceBoxPetSuppliesGeneral.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof Food) {
                jumpOnEnter(choiceBoxPetSuppliesGeneral,datePickerExpirationDateFood);
                jumpOnEnter(datePickerExpirationDateFood,textFieldFlavorFood);
            }
            else if (newValue instanceof Toy) {
                jumpOnEnter(choiceBoxPetSuppliesGeneral,textFieldMaterialToy);
                jumpOnEnter(textFieldMaterialToy,textFieldSizeToy);
            }
            else if (newValue instanceof Cage) {
                jumpOnEnter(choiceBoxPetSuppliesGeneral,textFieldMaterialCage);
                jumpOnEnter(textFieldMaterialCage,textFieldDimensionCage);
            }
            else if (newValue instanceof Accessory) {
                jumpOnEnter(choiceBoxPetSuppliesGeneral,textFieldTypeAccessory);
                jumpOnEnter(textFieldTypeAccessory,textFieldBrandAccessory);
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

    public void dragAndDropTheImage() {
        hBoxImageView.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles() || db.hasUrl() || db.hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        hBoxImageView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;


            try {
                int currentCount = imageData.size();
                int remainingSlots = 5 - currentCount;

                if (remainingSlots <= 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Chỉ được chọn tối đa 5 ảnh!");
                    alert.showAndWait();
                    event.setDropCompleted(false);
                    event.consume();
                    return;
                }

                if (db.hasFiles()) {
                    // 📁 Ảnh từ máy
                    List<File> files = db.getFiles();

                    List<byte[]> fileByte = new ArrayList<>();
                    for (File file : files) {
                        try {
                            fileByte.add(Files.readAllBytes(file.toPath()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

//                    for (int i = 0; i < Math.min(files.size(), remainingSlots); i++) {
//                        File file = files.get(i);
//                        Image image = ImageUtils.cropToImageView(new Image(file.toURI().toString()), 50, 50);
//                        byte[] data = Files.readAllBytes(file.toPath());
//
//                        images.add(image);
//                        imageData.add(data);
//
//                        ImageView imageView = new ImageView(image);
//                        imageView.setFitHeight(50);
//                        imageView.setFitWidth(50);
//                        imageView.setPreserveRatio(true);
//
//                        hBoxImageView.getChildren().add(imageView);
//
//                        success = true;
//                    }

                    if (files.size() > remainingSlots) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Chỉ thêm được " + remainingSlots + " ảnh nữa.");
                        alert.showAndWait();
                    }
                    else {
                        refreshImagesOnHBox(fileByte);
                        success = true;
                    }

                } else {

                    if (remainingSlots <= 0) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Chỉ được chọn tối đa 5 ảnh!");
                        alert.showAndWait();
                        event.setDropCompleted(false);
                        event.consume();
                        return;
                    }

                    // 🌐 Ảnh từ web

                    String url = db.hasUrl() ? db.getUrl() : db.getString();
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

                            byte[] data = baos.toByteArray();
                            List<byte[]> fileByte = new ArrayList<>();
                            fileByte.add(data);

                            refreshImagesOnHBox(fileByte);

//                            Image image = ImageUtils.cropToImageView(new Image(new ByteArrayInputStream(data)), 50, 50);
//
//                            ImageView imageView = new ImageView(image);
//                            imageView.setFitHeight(50);
//                            imageView.setFitWidth(50);
//                            imageView.setPreserveRatio(true);
//
//                            images.add(image);
//                            imageData.add(data);
//                            hBoxImageView.getChildren().add(imageView);
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

    private static ObservableList<Supplier> listSupplier;

    Product product;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listSupplier = FXCollections.observableArrayList(SelectSupplier.getAllSuppliers());

        griPaneVision();

        insertPetSupplies();

        insertQuantity();

        exceptions();

        comboBoxSupplierGeneral.setItems(listSupplier);

        setButtonAddDisable();

        buttonEnter();

        dragAndDropTheImage();
    }
}
