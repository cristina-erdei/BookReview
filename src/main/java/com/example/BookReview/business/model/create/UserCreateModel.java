package com.example.BookReview.business.model.create;

public class UserCreateModel {
    private String password;
    private String email;

    public UserCreateModel() {
    }

    public UserCreateModel(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
