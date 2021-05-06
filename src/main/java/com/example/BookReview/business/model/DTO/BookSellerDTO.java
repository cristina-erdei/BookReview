package com.example.BookReview.business.model.DTO;

public class BookSellerDTO extends UserDTO {
    private String name;

    public BookSellerDTO() {
    }

    public BookSellerDTO(String email, String name) {
        super(email);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
