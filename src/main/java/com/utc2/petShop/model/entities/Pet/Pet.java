package com.utc2.petShop.model.entities.Pet;


import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.entities.vaccine.Vaccine;
import com.utc2.petShop.model.entities.vaccine.Vaccine_Pet;
import com.utc2.petShop.model.implement.IPet;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Pet implements IPet {
    private ObjectProperty<byte[]> image;
    private IntegerProperty id;
    private StringProperty name;
    private IntegerProperty age;
    private BooleanProperty gender;
    private DoubleProperty price;
    private StringProperty healthStatus;
    private StringProperty origin;
    private DoubleProperty weight;
    private StringProperty furColor;
    private StringProperty description;
    private ObjectProperty<Supplier> supplier;
    private ListProperty<Vaccine_Pet> vaccines;

    public Pet(byte[] image, int id, String name, int age, boolean gender, double price, String healthStatus, String origin, double weight, String furColor, String description, Supplier supplier, List<Vaccine_Pet> vaccines) {
        this.image = new SimpleObjectProperty<byte[]>(image);
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.gender = new SimpleBooleanProperty(gender);
        this.price = new SimpleDoubleProperty(price);
        this.healthStatus = new SimpleStringProperty(healthStatus);
        this.origin = new SimpleStringProperty(origin);
        this.weight = new SimpleDoubleProperty(weight);
        this.furColor = new SimpleStringProperty(furColor);
        this.description = new SimpleStringProperty(description);
        this.supplier = new SimpleObjectProperty<Supplier>(supplier);
        this.vaccines = new SimpleListProperty<Vaccine_Pet>(FXCollections.observableArrayList(vaccines));
    }

    public Pet() {
        this.image = new SimpleObjectProperty<byte[]>(null);
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.age = new SimpleIntegerProperty(0);
        this.gender = new SimpleBooleanProperty(true);
        this.price = new SimpleDoubleProperty(0);
        this.healthStatus = new SimpleStringProperty("");
        this.origin = new SimpleStringProperty("");
        this.weight = new SimpleDoubleProperty(0);
        this.furColor = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");
        this.supplier = new SimpleObjectProperty<Supplier>( new Supplier());
        this.vaccines = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public byte[] getImage() {
        return image.get();
    }

    public ObjectProperty<byte[]> imageProperty() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image.set(image);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public boolean isGender() {
        return gender.get();
    }

    public BooleanProperty genderProperty() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender.set(gender);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getHealthStatus() {
        return healthStatus.get();
    }

    public StringProperty healthStatusProperty() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus.set(healthStatus);
    }

    public String getOrigin() {
        return origin.get();
    }

    public StringProperty originProperty() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin.set(origin);
    }

    public double getWeight() {
        return weight.get();
    }

    public DoubleProperty weightProperty() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }

    public String getFurColor() {
        return furColor.get();
    }

    public StringProperty furColorProperty() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor.set(furColor);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Supplier getSupplier() {
        return supplier.get();
    }

    public ObjectProperty<Supplier> supplierProperty() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier.set(supplier);
    }

    public ObservableList<Vaccine_Pet> getVaccines() {
        return vaccines.get();
    }

    public ListProperty<Vaccine_Pet> vaccinesProperty() {
        return vaccines;
    }

    public void setVaccines(ObservableList<Vaccine_Pet> vaccines) {
        this.vaccines.set(vaccines);
    }

    @Override
    public String toString() {
        return "";
    }

// phương thức câ thiết
    @Override
    public void displayInfo() {

    }

    @Override
    public void updateInfo() {

    }

    @Override
    public int calculateAge() {
        return 0;
    }

    @Override
    public void updateAvailability() {

    }
}
