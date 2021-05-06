package com.example.BookReview.business.service.interfaces;

import com.example.BookReview.business.model.base.Book;
import com.example.BookReview.business.model.base.BookSeller;
import com.example.BookReview.business.model.create.BookCreateModel;
import com.example.BookReview.business.model.create.BookSellerCreateModel;
import com.example.BookReview.data.model.AdministratorDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookSellerService {
    List<BookSeller> findAll();

    BookSeller findById(Long id);

    BookSeller findAllByAuthenticationToken(String authenticationToken);

    BookSeller findByAuthenticationToken(String authenticationToken);

    BookSeller create(BookSellerCreateModel createModel);

    BookSeller update(Long id, BookSellerCreateModel newValue);

    BookSeller deleteById(Long id);

}
