package com.utc2.petShop.model.entities.Pet;

public enum ERabbitBreed {
    FlemishGiant("Flemish Giant"), MiniRex("Mini Rex"), Angora("Angora"), HollandLop("Holland Lop");

    String breed;

    ERabbitBreed(String breed) {
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }
}
