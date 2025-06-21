package com.utc2.petShop.model.entities.Image;

import javafx.beans.property.*;

public class ImageByte {
    private IntegerProperty id;
    private ObjectProperty<byte[]> image;

    public ImageByte(int id, byte[] image) {
        this.id = new SimpleIntegerProperty(id);
        this.image = new SimpleObjectProperty<>(image);
    }

    public ImageByte() {
        this.id = new SimpleIntegerProperty(0);
        this.image = new SimpleObjectProperty<>(null);
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

    public byte[] getImage() {
        return image.get();
    }

    public ObjectProperty<byte[]> imageProperty() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image.set(image);
    }
}
