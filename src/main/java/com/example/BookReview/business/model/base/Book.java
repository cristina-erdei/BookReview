package com.example.BookReview.business.model.base;

import com.example.BookReview.data.model.BookDB;
import com.example.BookReview.helper.BookGenre;
import com.example.BookReview.helper.Language;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Book {
    private Long id;

    private String title;
    private LocalDate publicationDate;
    private int totalNumberOfRatings;
    private double meanRating;
    private BookGenre genre;
    private Language language;
    private String description;

    private List<Author> authors;


    public Book() {
    }

    public Book(String title, LocalDate publicationDate, int totalNumberOfRatings, double meanRating, BookGenre genre, Language language, String description, List<Author> authors) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.totalNumberOfRatings = totalNumberOfRatings;
        this.meanRating = meanRating;
        this.genre = genre;
        this.language = language;
        this.description = description;
        this.authors = authors;
    }

    public Book(BookDB bookDB) {
        this.id = bookDB.getId();
        this.title = bookDB.getTitle();
        this.publicationDate = bookDB.getPublicationDate();
        this.totalNumberOfRatings = bookDB.getTotalNumberOfRatings();
        this.meanRating = bookDB.getMeanRating();
        this.genre = bookDB.getGenre();
        this.language = bookDB.getLanguage();
        this.description = bookDB.getDescription();
        this.authors = bookDB.getAuthors().stream().map(Author::new).collect(Collectors.toList());
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

}
