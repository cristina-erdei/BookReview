package com.example.BookReview.business.controller;

import com.example.BookReview.business.model.DTO.BookDTO;
import com.example.BookReview.business.model.base.Administrator;
import com.example.BookReview.business.model.base.Book;
import com.example.BookReview.business.model.create.BookCreateModel;
import com.example.BookReview.business.model.strategy.*;
import com.example.BookReview.business.service.implementation.AdministratorServiceImplementation;
import com.example.BookReview.business.service.implementation.BookServiceImplementation;
import com.example.BookReview.helper.BookGenre;
import com.example.BookReview.helper.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {


    @Autowired
    private BookServiceImplementation bookService;
    @Autowired
    private AdministratorServiceImplementation administratorService;


    BookSearching bookSearching = new BookSearching(new BookTitleSearchingStrategy());

    @GetMapping("/getAll")
    public ResponseEntity<List<BookDTO>> findAll() {
        List<Book> books = bookService.findAll();

        if (books == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(books.stream().map(BookDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) {
        Book book = bookService.findById(id);

        if (book == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookDTO(book), HttpStatus.OK);
    }

    @GetMapping("/getByGenre/{genre}")
    public ResponseEntity<List<BookDTO>> findAllByGenre(@PathVariable BookGenre genre) {
        List<Book> books = bookService.findAllByGenre(genre);

        if (books == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(books.stream().map(BookDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/getByLanguage/{language}")
    public ResponseEntity<List<BookDTO>> findAllByLanguage(@PathVariable Language language) {
        List<Book> books = bookService.findAllByLanguage(language);

        if (books == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(books.stream().map(BookDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BookDTO> create(@RequestBody BookCreateModel createModel, @RequestHeader("Token") String token) {
        Administrator user = administratorService.findByAuthenticationToken(token);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Book book = bookService.create(createModel);

        return new ResponseEntity<>(new BookDTO(book), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody BookCreateModel newValue, @RequestHeader("Token") String token) {
        Administrator user = administratorService.findByAuthenticationToken(token);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Book book = bookService.update(id, newValue);

        if (book == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookDTO(book), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable Long id, @RequestHeader("Token") String token) {
        Administrator user = administratorService.findByAuthenticationToken(token);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        boolean success = bookService.deleteById(id);

        if (!success) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> search(@RequestBody SearchRequestModel model){
        switch (model.getStrategy()) {
            case BookTitle:
                bookSearching.setStrategy(new BookTitleSearchingStrategy());
                break;
            case AuthorID:
                bookSearching.setStrategy(new AuthorIDSearchingStrategy());
                break;
            case AuthorName:
                bookSearching.setStrategy(new AuthorNameSearchingStrategy());
                break;

            default:
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List<Book> books;
        if(bookService.findAll() == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            books = bookSearching.findBooks(bookService.findAll(), model.getData());
        } catch (NumberFormatException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(books.stream().map(BookDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

}

