package com.example.BookReview.business.model.strategy;

import com.example.BookReview.business.model.base.Book;

import java.util.ArrayList;
import java.util.List;

public class BookTitleSearchingStrategy implements SearchingStrategyAbstract {
    @Override
    public List<Book> findBook(List<Book> books, String data) {
        List<Book> result = new ArrayList<>();
        for(Book book : books) {
            if(book.getTitle().toLowerCase().contains(data.toLowerCase())){
                result.add(book);
            }
        }
        return result;
    }
}
