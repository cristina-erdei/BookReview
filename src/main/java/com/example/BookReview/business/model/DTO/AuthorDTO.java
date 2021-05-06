package com.example.BookReview.business.model.DTO;

import java.time.LocalDate;

public class AuthorDTO {
    private Long id;

    private String fullName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath; //TODO: figure out default value if not dead yet and how to handle it???

    public AuthorDTO() {
    }

    public AuthorDTO(String fullName, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
}
