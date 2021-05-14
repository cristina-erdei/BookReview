package com.example.BookReview.business.controller;

import com.example.BookReview.business.service.interfaces.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookReview")
public class BookReviewController {


    @Qualifier("bookReviewService")
    @Autowired
    private BookReviewService bookReviewService;
}
