package com.example.BookReview.business.model.create;

public class BookRatingCreateModel {
    private int rating;

    private Long bookId;
    private Long readerId;

    public BookRatingCreateModel() {
    }

    public BookRatingCreateModel(int rating, Long book, Long reader) {
        this.rating = rating;
        this.bookId = book;
        this.readerId = reader;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }
}
