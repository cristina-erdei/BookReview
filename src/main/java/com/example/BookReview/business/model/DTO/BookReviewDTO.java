package com.example.BookReview.business.model.DTO;

public class BookReviewDTO {
    private Long id;

    private String review;

    private BookDTO book;
    private ReaderDTO reader;

    public BookReviewDTO() {
    }

    public BookReviewDTO(String review, BookDTO book, ReaderDTO reader) {
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

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public ReaderDTO getReader() {
        return reader;
    }

    public void setReader(ReaderDTO reader) {
        this.reader = reader;
    }
}
