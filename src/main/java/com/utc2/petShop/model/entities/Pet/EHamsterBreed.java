package com.utc2.petShop.model.entities.Pet;

public enum EHamsterBreed {
    HamsterSyrian("Hamster Syrian (Golden Hamster)"), HamsterDwarf("Hamster Dwarf (Hamster lùn)"), HamsterChinese("Hamster Chinese"), HamsterTeddyBear("Hamster Teddy Bear"), HamsterDuoiDai("Hamster Đuôi Dài");

    String breed;

    EHamsterBreed(String breed) {
        this.breed = breed;
    }
}
