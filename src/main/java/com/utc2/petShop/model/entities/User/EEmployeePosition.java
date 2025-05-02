package com.utc2.petShop.model.entities.User;

public enum EEmployeePosition {
    quanLy("Quản lý"), nhanVienBanHang("Nhân viên bán hàng"), tuVanVien("Tư vấn viên"), nhanVienChamSoc("Nhân viên chăm sóc");

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
