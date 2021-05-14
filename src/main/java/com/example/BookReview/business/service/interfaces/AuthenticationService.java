package com.example.BookReview.business.service.interfaces;

import com.example.BookReview.business.model.login.LoginRequestModel;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    String login(LoginRequestModel loginRequestModel);
    boolean logout(String token);
}
