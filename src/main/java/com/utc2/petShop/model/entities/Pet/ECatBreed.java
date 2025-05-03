package com.utc2.petShop.model.entities.Pet;

public enum ECatBreed {
    MeoAnhLongDai("Mèo Anh Lông Dài"), MeoAnhLongNgan("Mèo Anh Lông Ngắn"), MeoBaTu("Mèo Ba Tư"), MeoMunchkin("Mèo Munchkin"), MeoMuop("Mèo Mướp"), MeoCam("Mèo Cam");

    String breed;

    ECatBreed(String breed) {
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        return this.breed;
    }
}
