package com.utc2.petShop.model.entities.User;

public enum EEmployeePosition {
    ChuTinh("Chủ tịch"), GiamDoc("Giám đốc"), BaoVe("Bảo vệ");

    String Position;

    EEmployeePosition(String Position) {
        this.Position = Position;
    }

    public String getPosition() {
        return Position;
    }

    @Override
    public String toString() {
        return Position;
    }
}
