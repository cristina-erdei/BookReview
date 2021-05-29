package com.example.BookReview.business.model.base;

import com.example.BookReview.data.model.AuthorDB;

import java.time.LocalDate;

public class Author {
    private Long id;

    private String fullName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

    public Author() {
    }

    public Author(String fullName, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }

    public Author(AuthorDB authorDB) {
        this.id = authorDB.getId();
        this.fullName = authorDB.getFullName();
        this.dateOfBirth = authorDB.getDateOfBirth();
        this.dateOfDeath = authorDB.getDateOfDeath();
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
