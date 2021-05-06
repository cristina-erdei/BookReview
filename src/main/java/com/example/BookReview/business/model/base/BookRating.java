package com.example.BookReview.business.model.base;

public class BookRating {
    private Long id;

    private int rating;

    private Book book;
    private Reader reader;

    public BookRating() {
    }

    public BookRating(int rating, Book book, Reader reader) {
        this.rating = rating;
        this.book = book;
        this.reader = reader;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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
