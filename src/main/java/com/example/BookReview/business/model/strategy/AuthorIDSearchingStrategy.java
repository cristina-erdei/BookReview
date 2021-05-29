package com.example.BookReview.business.model.strategy;

import com.example.BookReview.business.model.base.Author;
import com.example.BookReview.business.model.base.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorIDSearchingStrategy implements SearchingStrategyAbstract {
    //expects Long id given as data
    @Override
    public List<Book> findBook(List<Book> books, String data) throws NumberFormatException {
        Long authorId = Long.parseLong(data); //throws NumberFormatException in case of wrong data
        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            for(Author author: book.getAuthors()){
                if(author.getId().equals(authorId)){
                    result.add(book);
                }
            }
        }

        return result;
    }
}
