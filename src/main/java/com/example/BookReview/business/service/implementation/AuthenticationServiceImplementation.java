package com.example.BookReview.business.service.implementation;

import com.example.BookReview.business.model.base.Administrator;
import com.example.BookReview.business.model.base.BookSeller;
import com.example.BookReview.business.model.base.Reader;
import com.example.BookReview.business.model.login.LoginRequestModel;
import com.example.BookReview.business.service.interfaces.AuthenticationService;
import com.example.BookReview.helper.AppConstants;
import com.example.BookReview.helper.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {

    @Autowired
    private AdministratorServiceImplementation administratorService;
    @Autowired
    private ReaderServiceImplementation readerService;
    @Autowired
    private BookSellerServiceImplementation bookSellerService;


    @Override
    public String login(LoginRequestModel loginRequestModel) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedPass = encoder.encodeToString(loginRequestModel.getPassword().getBytes(StandardCharsets.UTF_8));
        String authenticationToken = TokenGenerator.generateNewToken(AppConstants.authenticationTokenLength);


        //check if admin
        Administrator administrator = administratorService.findByEmail(loginRequestModel.getEmail());

        if(administrator != null){
            if(!encodedPass.equals(administrator.getPassword())){
                return null;
            }

            boolean success = administratorService.updateAuthenticationToken(administrator.getId(), authenticationToken);
             if(!success){
                 return null;
             }
             return authenticationToken;
        }

        //check if reader
        Reader reader = readerService.findByEmail(loginRequestModel.getEmail());

        if(reader != null){
            if(!encodedPass.equals(reader.getPassword())){
                return null;
            }

            boolean success = readerService.updateAuthenticationToken(reader.getId(), authenticationToken);
            if(!success){
                return null;
            }
            return authenticationToken;
        }


        //check if seller
        BookSeller bookSeller = bookSellerService.findByEmail(loginRequestModel.getEmail());

        if(bookSeller != null){
            if(!encodedPass.equals(bookSeller.getPassword())){
                return null;
            }

            boolean success = bookSellerService.updateAuthenticationToken(bookSeller.getId(), authenticationToken);
            if(!success){
                return null;
            }
            return authenticationToken;
        }

        return null;
    }

    @Override
    public boolean logout(String token) {
        //check if admin
        Administrator administrator = administratorService.findByAuthenticationToken(token);

        if(administrator != null){
            return administratorService.updateAuthenticationToken(administrator.getId(), null);
        }

        //check if reader
        Reader reader = readerService.findByAuthenticationToken(token);

        if(reader != null){
            return readerService.updateAuthenticationToken(reader.getId(), null);
        }

        //check if seller
        BookSeller bookSeller = bookSellerService.findByAuthenticationToken(token);

        if(bookSeller != null){
            return bookSellerService.updateAuthenticationToken(bookSeller.getId(), null);
        }

        return false;
    }
}
