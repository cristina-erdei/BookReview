package com.example.BookReview.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookReviewDB {
    private Long id;

    private String review;

    private BookDB book;
    private ReaderDB reader;

    public BookReviewDB() {
    }

    public BookReviewDB(String review, BookDB book, ReaderDB reader) {
        this.review = review;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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
