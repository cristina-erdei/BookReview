package com.example.BookReview.business.service.implementation;

import com.example.BookReview.business.model.base.Administrator;
import com.example.BookReview.business.model.login.LoginRequestModel;
import com.example.BookReview.business.service.interfaces.AuthenticationService;
import com.example.BookReview.data.model.AdministratorDB;
import com.example.BookReview.data.model.BookSellerDB;
import com.example.BookReview.data.model.ReaderDB;
import com.example.BookReview.data.repository.AdministratorRepository;
import com.example.BookReview.data.repository.BookSellerRepository;
import com.example.BookReview.data.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {


    @Qualifier("readerRepository")
    @Autowired
    private ReaderRepository readerRepository;
    @Qualifier("administratorRepository")
    @Autowired
    private AdministratorRepository administratorRepository;
    @Qualifier("bookSellerRepository")
    @Autowired
    private BookSellerRepository bookSellerRepository;


    @Override
    public String login(LoginRequestModel loginRequestModel) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedPass = encoder.encodeToString(loginRequestModel.getPassword().getBytes(StandardCharsets.UTF_8));



        //check if admin

        AdministratorDB administratorDBOptional = administratorRepository.findByEmail(loginRequestModel.getEmail());

        if(administratorDBOptional != null){

        }

        //check if reader

        ReaderDB readerDBOptional = readerRepository.findByEmail(loginRequestModel.getEmail());

        //check if seller

        BookSellerDB bookSellerDBOptional = bookSellerRepository.findByEmail(loginRequestModel.getEmail());


        return null;
    }

    @Override
    public void logout(String token) {
        //check if admin
        //check if reader
        //check if seller
    }
}
