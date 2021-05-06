package com.example.BookReview.business.model.create;

public class BookReviewCreateModel {
    private String review;

    private Long bookId;
    private Long readerId;

    public BookReviewCreateModel() {
    }

    public BookReviewCreateModel(String review, Long book, Long reader) {
        this.review = review;
        this.bookId = book;
        this.readerId = reader;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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
