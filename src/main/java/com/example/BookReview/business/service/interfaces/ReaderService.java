package com.example.BookReview.business.service.interfaces;

import com.example.BookReview.business.model.base.Reader;
import com.example.BookReview.business.model.create.ReaderCreateModel;
import com.example.BookReview.data.model.AdministratorDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReaderService {
    List<Reader> findAll();

    Reader findById(Long id);

    Reader findByAuthenticationToken(String authenticationToken);

    Reader create(ReaderCreateModel createModel);

    Reader update(Long id, ReaderCreateModel newValue);

    Reader deleteById(Long id);

}
