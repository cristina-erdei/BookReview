package com.example.BookReview.business.service.interfaces;

import com.example.BookReview.business.model.base.Author;
import com.example.BookReview.business.model.base.Book;
import com.example.BookReview.business.model.create.BookCreateModel;
import com.example.BookReview.helper.BookGenre;
import com.example.BookReview.helper.Language;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> findAll();

    Book findById(Long id);

    List<Book> findAllByAuthors(List<Author> authors);

    List<Book> findAllByGenre(BookGenre genre);

    List<Book> findAllByLanguage(Language language);

    Book create(BookCreateModel createModel);

    Book update(Long id, BookCreateModel newValue);

    Book deleteById(Long id);

}

