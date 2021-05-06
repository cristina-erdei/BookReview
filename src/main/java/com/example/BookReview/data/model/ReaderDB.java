package com.example.BookReview.data.model;

import javax.persistence.Entity;

@Entity
public class ReaderDB extends UserDB {
    private String name;

    public ReaderDB() {
    }

    public ReaderDB(String password, String email, String authenticationToken, String name) {
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
