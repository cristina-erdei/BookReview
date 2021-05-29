package com.example.BookReview.business.model.strategy;

import com.example.BookReview.business.model.base.Book;

import java.util.List;

public class BookSearching {

    private SearchingStrategyAbstract strategy;

    public BookSearching(SearchingStrategyAbstract strategy) {
        this.strategy = strategy;
    }

    public List<Book> findBooks(List<Book> allBooks, String data){
        return strategy.findBook(allBooks, data);
    }

    public SearchingStrategyAbstract getStrategy() {
        return strategy;
    }

    public void setStrategy(SearchingStrategyAbstract strategy) {
        this.strategy = strategy;
    }
}
