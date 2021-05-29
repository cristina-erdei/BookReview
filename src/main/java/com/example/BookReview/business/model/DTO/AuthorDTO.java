package com.example.BookReview.business.model.DTO;

import com.example.BookReview.business.model.base.Author;

import java.time.LocalDate;

public class AuthorDTO {
    private Long id;

    private String fullName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

    public AuthorDTO() {
    }

    public AuthorDTO(String fullName, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.fullName = author.getFullName();
        this.dateOfBirth = author.getDateOfBirth();
        this.dateOfDeath = author.getDateOfDeath();
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
