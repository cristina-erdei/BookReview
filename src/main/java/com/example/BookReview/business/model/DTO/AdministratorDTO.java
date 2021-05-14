package com.example.BookReview.business.model.DTO;

import com.example.BookReview.business.model.base.Administrator;

public class AdministratorDTO extends UserDTO {
    private String name;

    public AdministratorDTO() {
    }

    public AdministratorDTO(String email, String name) {
        super(email);
        this.name = name;
    }

    public AdministratorDTO(Administrator administrator) {
        super(administrator.getId(), administrator.getEmail());
        this.name = administrator.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
