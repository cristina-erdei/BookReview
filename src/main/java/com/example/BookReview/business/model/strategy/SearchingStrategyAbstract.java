package com.example.BookReview.business.model.strategy;

import com.example.BookReview.business.model.base.Book;

import java.util.List;

public interface SearchingStrategyAbstract {
    List<Book> findBook(List<Book> books, String data);
}
