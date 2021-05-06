package com.example.BookReview.business.model.base;

public class BookReview {
    private Long id;

    private String review;

    private Book book;
    private Reader reader;

    public BookReview() {
    }

    public BookReview(String review, Book book, Reader reader) {
        this.review = review;
        this.book = book;
        this.reader = reader;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
