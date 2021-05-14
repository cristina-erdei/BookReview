package com.example.BookReview.business.model.base;

import com.example.BookReview.data.model.ReaderDB;

public class Reader extends User {
    private String name;

    public Reader() {
    }

    public Reader(String password, String email, String name) {
        super(password, email);
        this.name = name;
    }

    public Reader(ReaderDB readerDB) {
        super(readerDB.getId(), readerDB.getPassword(), readerDB.getEmail());
        this.name = readerDB.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
