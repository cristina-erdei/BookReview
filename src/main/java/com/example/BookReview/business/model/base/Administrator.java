package com.example.BookReview.business.model.base;

public class Administrator extends User {
    private String name;

    public Administrator() {
    }

    public Administrator(String password, String email, String name) {
        super(password, email);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
