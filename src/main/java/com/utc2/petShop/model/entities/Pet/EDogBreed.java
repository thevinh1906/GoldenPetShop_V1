package com.utc2.petShop.model.entities.Pet;

public enum EDogBreed {
    Golden("Golden"), Poodle("Poodle"), Beagle("Beagle"), Chihuahua("Chihuahua"), Husky("Husky"), Alaskan("Alaskan"), ChoCo("Chó Cỏ"), CauVang("Cậu Vàng");

    String breed;

    EDogBreed(String breed) {
        this.breed = breed;
    }
}
