package com.example.BookReview.business.model.DTO;

public class AdministratorDTO extends UserDTO {
    private String name;

    public AdministratorDTO() {
    }

    public AdministratorDTO(String email, String name) {
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
