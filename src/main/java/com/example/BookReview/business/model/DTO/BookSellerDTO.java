package com.example.BookReview.business.model.DTO;

import com.example.BookReview.business.model.base.BookSeller;

public class BookSellerDTO extends UserDTO {
    private String name;

    public BookSellerDTO() {
    }

    public BookSellerDTO(String email, String name) {
        super(email);
        this.name = name;
    }

    public BookSellerDTO(BookSeller bookSeller) {
        super(bookSeller.getId(), bookSeller.getEmail());
        this.name = bookSeller.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
