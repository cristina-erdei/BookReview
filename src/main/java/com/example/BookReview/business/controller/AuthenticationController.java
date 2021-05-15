package com.example.BookReview.business.controller;

import com.example.BookReview.business.model.login.LoginRequestModel;
import com.example.BookReview.business.service.implementation.AuthenticationServiceImplementation;
import com.example.BookReview.business.service.implementation.AuthorServiceImplementation;
import com.example.BookReview.business.service.interfaces.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {


    @Autowired
    private AuthenticationServiceImplementation authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestModel loginRequestModel) {
        String authenticationToken = authenticationService.login(loginRequestModel);

        if(authenticationToken == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(authenticationToken, HttpStatus.OK);
    }
    public ResponseEntity logout(@RequestHeader("Token") String token) {
        boolean success = authenticationService.logout(token);

        if(!success){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
