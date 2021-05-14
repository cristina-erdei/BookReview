package com.example.BookReview.business.model.base;

import com.example.BookReview.data.model.AdministratorDB;

public class Administrator extends User {
    private String name;

    public Administrator() {
    }

    public Administrator(String password, String email, String name) {
        super(password, email);
        this.name = name;
    }

    public Administrator(AdministratorDB administratorDB) {
        super(administratorDB.getId(), administratorDB.getPassword(), administratorDB.getEmail());
        this.name = administratorDB.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
