package com.example.BookReview.business.service.interfaces;

import com.example.BookReview.business.model.base.Author;
import com.example.BookReview.business.model.base.Book;
import com.example.BookReview.business.model.create.AuthorCreateModel;
import com.example.BookReview.business.model.create.BookCreateModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    List<Author> findAll();

    Author findById(Long id);

    Author create(AuthorCreateModel createModel);

    Author update(Long id, AuthorCreateModel newValue);

    Author deleteById(Long id);

}
