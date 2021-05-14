package com.example.BookReview.business.service.interfaces;

import com.example.BookReview.business.model.base.Administrator;
import com.example.BookReview.business.model.base.Book;
import com.example.BookReview.business.model.create.AdministratorCreateModel;
import com.example.BookReview.business.model.create.BookCreateModel;
import com.example.BookReview.data.model.AdministratorDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdministratorService {
    List<Administrator> findAll();

    Administrator findById(Long id);

    Administrator findByAuthenticationToken(String authenticationToken);

    Administrator findByEmail(String email);

    Administrator create(AdministratorCreateModel createModel);

    Administrator update(Long id, AdministratorCreateModel newValue);

    Administrator deleteById(Long id);

}
