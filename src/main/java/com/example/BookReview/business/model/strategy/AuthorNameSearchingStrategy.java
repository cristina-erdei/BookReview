package com.example.BookReview.business.model.strategy;

import com.example.BookReview.business.model.base.Author;
import com.example.BookReview.business.model.base.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AuthorNameSearchingStrategy implements SearchingStrategyAbstract {
    @Override
    public List<Book> findBook(List<Book> books, String data) {
        List<Book> result = new ArrayList<>();
        for(Book book : books){
            for(Author author: book.getAuthors()){
                if(author.getFullName().toLowerCase().contains(data.toLowerCase())){
                    result.add(book);
                }
            }
        }
        return result;
    }
}
