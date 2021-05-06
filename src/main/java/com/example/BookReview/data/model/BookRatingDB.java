package com.example.BookReview.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookRatingDB {
    private Long id;

    private int rating;

    private BookDB book;
    private ReaderDB reader;

    public BookRatingDB() {
    }

    public BookRatingDB(int rating, BookDB book, ReaderDB reader) {
        this.rating = rating;
        this.book = book;
        this.reader = reader;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @ManyToOne
    public BookDB getBook() {
        return book;
    }

    public void setBook(BookDB book) {
        this.book = book;
    }

    @ManyToOne
    public ReaderDB getReader() {
        return reader;
    }

    public void setReader(ReaderDB reader) {
        this.reader = reader;
    }
}
