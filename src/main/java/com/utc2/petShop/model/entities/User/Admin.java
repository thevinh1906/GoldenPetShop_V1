package com.utc2.petShop.model.entities.User;

import java.time.LocalDate;

public class Admin extends User {
    public Admin(int id, String username, String password, String name, boolean gender, String email, String phoneNumber, String address, LocalDate birthDay, LocalDate creationDate) {
        super(id, username, password, name, gender, email, phoneNumber, address, birthDay, creationDate);
    }

    public Admin() {
        super();
    }
}
