package com.example.BookReview.business.controller;

import com.example.BookReview.business.model.DTO.BookSellerDTO;
import com.example.BookReview.business.model.base.Administrator;
import com.example.BookReview.business.model.base.BookSeller;
import com.example.BookReview.business.model.create.BookSellerCreateModel;
import com.example.BookReview.business.service.implementation.AdministratorServiceImplementation;
import com.example.BookReview.business.service.implementation.BookSellerServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookSeller")
public class BookSellerController {


    @Autowired
    private BookSellerServiceImplementation bookSellerService;
    @Autowired
    private AdministratorServiceImplementation administratorService;


    @GetMapping("/getAll")
    public ResponseEntity<List<BookSellerDTO>> findAll() {
        List<BookSeller> bookSellers = bookSellerService.findAll();

        if (bookSellers == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bookSellers.stream().map(BookSellerDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<BookSellerDTO> findById(@PathVariable Long id) {
        BookSeller bookSeller = bookSellerService.findById(id);

        if (bookSeller == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookSellerDTO(bookSeller), HttpStatus.OK);
    }


    @GetMapping("/getByToken/{authenticationToken}")
    public ResponseEntity<BookSellerDTO> findByAuthenticationToken(@PathVariable String authenticationToken) {
        BookSeller bookSeller = bookSellerService.findByAuthenticationToken(authenticationToken);

        if (bookSeller == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookSellerDTO(bookSeller), HttpStatus.OK);
    }


    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<BookSellerDTO> findByEmail(@PathVariable String email) {
        BookSeller bookSeller = bookSellerService.findByEmail(email);

        if (bookSeller == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookSellerDTO(bookSeller), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<BookSellerDTO> create(@RequestBody BookSellerCreateModel createModel) {
        BookSeller bookSeller = bookSellerService.create(createModel);

        if(bookSeller == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookSellerDTO(bookSeller), HttpStatus.OK);
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<BookSellerDTO> update(@PathVariable Long id, @RequestBody BookSellerCreateModel newValue, @RequestHeader("Token") String token) {
        BookSeller user1 = bookSellerService.findByAuthenticationToken(token);
        Administrator user2 = administratorService.findByAuthenticationToken(token);
        if(user1 == null && user2 == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        if(user1 != null && !user1.getId().equals(id)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }


        BookSeller bookSeller = bookSellerService.update(id, newValue);

        if (bookSeller == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookSellerDTO(bookSeller), HttpStatus.OK);
    }

    @PostMapping("/updateToken/{id}")
    public ResponseEntity updateAuthenticationToken(@PathVariable Long id, @RequestBody String newToken) {
        boolean success = bookSellerService.updateAuthenticationToken(id, newToken);

        if (!success) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<BookSellerDTO> deleteById(@PathVariable Long id, @RequestHeader("Token") String token) {
        BookSeller user1 = bookSellerService.findByAuthenticationToken(token);
        Administrator user2 = administratorService.findByAuthenticationToken(token);
        if(user1 == null && user2 == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        if(user1 != null && !user1.getId().equals(id)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        BookSeller bookSeller = bookSellerService.deleteById(id);

        if (bookSeller == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new BookSellerDTO(bookSeller), HttpStatus.OK);
    }


}
