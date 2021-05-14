package com.example.BookReview.business.model.base;

import com.example.BookReview.data.model.BookSellerDB;

public class BookSeller extends User {
    private String name;

    public BookSeller() {
    }

    public BookSeller(String password, String email, String name) {
        super(password, email);
        this.name = name;
    }

    public BookSeller(BookSellerDB bookSellerDB) {
        super(bookSellerDB.getId(), bookSellerDB.getPassword(), bookSellerDB.getEmail());
        this.name = bookSellerDB.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
