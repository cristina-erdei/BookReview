package com.example.BookReview.business.controller;

import com.example.BookReview.business.model.DTO.BookReviewDTO;
import com.example.BookReview.business.model.base.BookReview;
import com.example.BookReview.business.model.base.BookReview;
import com.example.BookReview.business.model.create.BookReviewCreateModel;
import com.example.BookReview.business.model.create.BookReviewCreateModel;
import com.example.BookReview.business.service.implementation.BookReviewServiceImplementation;
import com.example.BookReview.business.service.interfaces.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookReview")
public class BookReviewController {


    @Autowired
    private BookReviewServiceImplementation bookReviewService;


    @GetMapping("/getAll")
    public ResponseEntity<List<BookReviewDTO>> findAll() {
        List<BookReview> bookReviews = bookReviewService.findAll();

        if (bookReviews == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bookReviews.stream().map(BookReviewDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<BookReviewDTO> findById(@PathVariable Long id) {
        BookReview bookReview = bookReviewService.findById(id);

        if (bookReview == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookReviewDTO(bookReview), HttpStatus.OK);
    }

    @GetMapping("/getByBookId/{book_id}")
    public ResponseEntity<List<BookReviewDTO>> findAllByBook_Id(@PathVariable Long book_id) {
        List<BookReview> bookReviews = bookReviewService.findAllByBook_Id(book_id);

        if (bookReviews == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bookReviews.stream().map(BookReviewDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/getByReaderId/{reader_id}")
    public ResponseEntity<List<BookReviewDTO>> findAllByReader_Id(@PathVariable Long reader_id) {
        List<BookReview> bookReviews = bookReviewService.findAllByReader_Id(reader_id);

        if (bookReviews == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bookReviews.stream().map(BookReviewDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BookReviewDTO> create(@RequestBody BookReviewCreateModel createModel) {
        BookReview bookReview = bookReviewService.create(createModel);

        return new ResponseEntity<>(new BookReviewDTO(bookReview), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<BookReviewDTO> update(@PathVariable Long id,@RequestBody BookReviewCreateModel newValue) {
        BookReview bookReview = bookReviewService.update(id, newValue);

        if (bookReview == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookReviewDTO(bookReview), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookReviewDTO> deleteById(@PathVariable Long id) {
        BookReview bookReview = bookReviewService.deleteById(id);

        if (bookReview == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookReviewDTO(bookReview), HttpStatus.OK);
    }
}
