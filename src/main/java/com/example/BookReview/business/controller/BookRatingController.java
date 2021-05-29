package com.example.BookReview.business.controller;

import com.example.BookReview.business.model.DTO.BookRatingDTO;
import com.example.BookReview.business.model.base.Administrator;
import com.example.BookReview.business.model.base.BookRating;
import com.example.BookReview.business.model.base.Reader;
import com.example.BookReview.business.model.create.BookRatingCreateModel;
import com.example.BookReview.business.service.implementation.BookRatingServiceImplementation;

import com.example.BookReview.business.service.implementation.ReaderServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookRating")
public class BookRatingController {


    @Autowired
    private BookRatingServiceImplementation bookRatingService;
    @Autowired
    private ReaderServiceImplementation readerService;


    @GetMapping("/getAll")
    public ResponseEntity<List<BookRatingDTO>> findAll() {
        List<BookRating> bookRatings = bookRatingService.findAll();

        if (bookRatings == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bookRatings.stream().map(BookRatingDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<BookRatingDTO> findById(@PathVariable Long id) {
        BookRating bookRating = bookRatingService.findById(id);

        if (bookRating == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookRatingDTO(bookRating), HttpStatus.OK);
    }

    @GetMapping("/getByBookId/{book_id}")
    public ResponseEntity<List<BookRatingDTO>> findAllByBook_Id(@PathVariable Long book_id) {
        List<BookRating> bookRatings = bookRatingService.findAllByBook_Id(book_id);

        if (bookRatings == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bookRatings.stream().map(BookRatingDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/getByReaderId/{reader_id}")
    public ResponseEntity<List<BookRatingDTO>> findAllByReader_Id(@PathVariable Long reader_id) {
        List<BookRating> bookRatings = bookRatingService.findAllByReader_Id(reader_id);

        if (bookRatings == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bookRatings.stream().map(BookRatingDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BookRatingDTO> create(@RequestBody BookRatingCreateModel createModel, @RequestHeader("Token") String token) {
        Reader user = readerService.findByAuthenticationToken(token);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        BookRating bookRating = bookRatingService.create(createModel);

        return new ResponseEntity<>(new BookRatingDTO(bookRating), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<BookRatingDTO> update(@PathVariable Long id, @RequestBody BookRatingCreateModel newValue, @RequestHeader("Token") String token) {
        Reader user = readerService.findByAuthenticationToken(token);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        BookRating bookRating = bookRatingService.update(id, newValue);

        if (bookRating == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookRatingDTO(bookRating), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookRatingDTO> deleteById(@PathVariable Long id, @RequestHeader("Token") String token) {
        Reader user = readerService.findByAuthenticationToken(token);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        BookRating bookRating = bookRatingService.deleteById(id);

        if (bookRating == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookRatingDTO(bookRating), HttpStatus.OK);
    }

}
