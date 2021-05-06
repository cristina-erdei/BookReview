package com.example.BookReview.business.model.create;

public class AdministratorCreateModel extends UserCreateModel {
    private String name;

    public AdministratorCreateModel() {
    }

    public AdministratorCreateModel(String password, String email, String name) {
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
