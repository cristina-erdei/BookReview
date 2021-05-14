package com.example.BookReview.business.model.DTO;

import com.example.BookReview.business.model.base.Reader;

public class ReaderDTO extends UserDTO {
    private String name;

    public ReaderDTO() {
    }

    public ReaderDTO(String email, String name) {
        super(email);
        this.name = name;
    }

    public ReaderDTO(Reader reader) {
        super(reader.getId(), reader.getEmail());
        this.name = reader.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
