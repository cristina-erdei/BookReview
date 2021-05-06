package com.example.BookReview.data.model;

import com.example.BookReview.helper.BookGenre;
import com.example.BookReview.helper.Language;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class BookDB {
    private Long id;

    private String title;
    private LocalDate publicationDate;
    private int totalNumberOfRatings;
    private double meanRating;
    //TODO: decide if observer for total number of reviews as well
    private BookGenre genre;
    private Language language;
    private String description;

    private List<AuthorDB> authors;


    public BookDB() {
    }

    public BookDB(String title, LocalDate publicationDate, int totalNumberOfRatings, double meanRating, BookGenre genre, Language language, String description, List<AuthorDB> authors) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.totalNumberOfRatings = totalNumberOfRatings;
        this.meanRating = meanRating;
        this.genre = genre;
        this.language = language;
        this.description = description;
        this.authors = authors;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getTotalNumberOfRatings() {
        return totalNumberOfRatings;
    }

    public void setTotalNumberOfRatings(int totalNumberOfRatings) {
        this.totalNumberOfRatings = totalNumberOfRatings;
    }

    public double getMeanRating() {
        return meanRating;
    }

    public void setMeanRating(double meanRating) {
        this.meanRating = meanRating;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany
    public List<AuthorDB> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDB> authors) {
        this.authors = authors;
    }

}
