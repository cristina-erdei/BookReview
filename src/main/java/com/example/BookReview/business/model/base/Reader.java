package com.example.BookReview.business.model.base;

public class Reader extends User {
    private String name;

    public Reader() {
    }

    public Reader(String password, String email, String name) {
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
