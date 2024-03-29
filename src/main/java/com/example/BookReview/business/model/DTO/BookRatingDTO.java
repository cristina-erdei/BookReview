package com.example.BookReview.business.model.DTO;

import com.example.BookReview.business.model.base.BookRating;

public class BookRatingDTO {
    private Long id;

    private int rating;

    private BookDTO book;
    private ReaderDTO reader;

    public BookRatingDTO() {
    }

    public BookRatingDTO(int rating, BookDTO book, ReaderDTO reader) {
        this.rating = rating;
        this.book = book;
        this.reader = reader;
    }

    public BookRatingDTO(BookRating bookRating) {
        this.id = bookRating.getId();
        this.rating = bookRating.getRating();
        this.book = new BookDTO(bookRating.getBook());
        this.reader = new ReaderDTO(bookRating.getReader());
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
