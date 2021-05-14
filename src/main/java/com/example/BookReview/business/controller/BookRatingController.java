package com.example.BookReview.business.controller;

import com.example.BookReview.business.service.interfaces.BookRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookRating")
public class BookRatingController {


    @Qualifier("bookRatingService")
    @Autowired
    private BookRatingService bookRatingService;
}
