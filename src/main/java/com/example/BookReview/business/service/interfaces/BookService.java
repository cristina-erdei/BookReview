package com.example.BookReview.business.service.interfaces;

import com.example.BookReview.business.model.base.*;
import com.example.BookReview.business.model.create.*;
import com.example.BookReview.business.model.strategy.SearchRequestModel;
import com.example.BookReview.helper.BookGenre;
import com.example.BookReview.helper.Language;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> findAll();

    Book findById(Long id);

    List<Book> findAllByGenre(BookGenre genre);

    List<Book> findAllByLanguage(Language language);

    Book create(BookCreateModel createModel);

    Book update(Long id, BookCreateModel newValue);

    boolean deleteById(Long id);

    List<Book> search(SearchRequestModel model);

}


