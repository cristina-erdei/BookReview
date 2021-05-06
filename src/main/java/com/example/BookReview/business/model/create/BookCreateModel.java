package com.example.BookReview.business.model.create;

import com.example.BookReview.helper.BookGenre;
import com.example.BookReview.helper.Language;

import java.util.List;

public class BookCreateModel {
    private String title;

    //publication date
    private int year;
    private int month;
    private int day;

    private int totalNumberOfRatings;
    private double meanRating;
    //TODO: decide if observer for total number of reviews as well
    private BookGenre genre;
    private Language language;
    private String description;

    private List<Long> authorIds;


    public BookCreateModel() {
    }

    public BookCreateModel(String title, int year, int month, int day, int totalNumberOfRatings, double meanRating, BookGenre genre, Language language, String description, List<Long> authors) {
        this.title = title;
        this.year = year;
        this.month = month;
        this.day = day;
        this.totalNumberOfRatings = totalNumberOfRatings;
        this.meanRating = meanRating;
        this.genre = genre;
        this.language = language;
        this.description = description;
        this.authorIds = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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

    public List<Long> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<Long> authorIds) {
        this.authorIds = authorIds;
    }
}
