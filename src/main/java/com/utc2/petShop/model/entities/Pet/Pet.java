package com.utc2.petShop.model.entities.Pet;


import com.utc2.petShop.model.entities.Product.Product;
import com.utc2.petShop.model.entities.Supplier.Supplier;
import com.utc2.petShop.model.implement.IPet;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Pet implements IPet {
    private ObjectProperty<Image> image;
    private IntegerProperty id;
    private StringProperty name;
    private IntegerProperty age;
    private BooleanProperty gender;
    private DoubleProperty price;
    private BooleanProperty vaccinated;
    private StringProperty healthStatus;
    private StringProperty origin;
    private DoubleProperty weight;
    private StringProperty furColor;
    private StringProperty description;
    private ObjectProperty<Supplier> supplier;

    public Pet(Image image, int id, String name, int age, boolean gender, double price, boolean vaccinated, String healthStatus, String origin, double weight, String furColor, String description, Supplier supplier) {
        this.image = new SimpleObjectProperty<Image>(image);
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.gender = new SimpleBooleanProperty(gender);
        this.price = new SimpleDoubleProperty(price);
        this.vaccinated = new SimpleBooleanProperty(vaccinated);
        this.healthStatus = new SimpleStringProperty(healthStatus);
        this.origin = new SimpleStringProperty(origin);
        this.weight = new SimpleDoubleProperty(weight);
        this.furColor = new SimpleStringProperty(furColor);
        this.description = new SimpleStringProperty(description);
        this.supplier = new SimpleObjectProperty<Supplier>(supplier);
    }

    public Pet() {
        this.image = new SimpleObjectProperty<Image>(null);
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.age = new SimpleIntegerProperty(0);
        this.gender = new SimpleBooleanProperty(true);
        this.price = new SimpleDoubleProperty(0);
        this.vaccinated = new SimpleBooleanProperty(true);
        this.healthStatus = new SimpleStringProperty("");
        this.origin = new SimpleStringProperty("");
        this.weight = new SimpleDoubleProperty(0);
        this.furColor = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");
        this.supplier = new SimpleObjectProperty<Supplier>( new Supplier());
    }

    public Image getImage() {
        return image.get();
    }

    public ObjectProperty<Image> imageProperty() {
        return image;
    }

    public void setImage(Image image) {
        this.image.set(image);
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

    public boolean isVaccinated() {
        return vaccinated.get();
    }

    public BooleanProperty vaccinatedProperty() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated.set(vaccinated);
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
