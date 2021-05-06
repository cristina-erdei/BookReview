package com.example.BookReview.data.model;

import javax.persistence.Entity;

@Entity
public class AdministratorDB extends UserDB {
    private String name;

    public AdministratorDB() {
    }

    public AdministratorDB(String password, String email, String authenticationToken, String name) {
        super(password, email, authenticationToken);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
