package com.utc2.petShop.model.implement;

import java.time.LocalDate;

public interface IVaccine {
    public boolean isExpired(LocalDate vaccinationDate); // Kiểm tra vaccine còn hiệu lực hay không
    public int getTotalDuration(); // Trả về tổng số ngày giữa các liều tiêm
}
