package com.example.BookReview.business.controller;

import com.example.BookReview.business.model.DTO.ReaderDTO;
import com.example.BookReview.business.model.base.Administrator;
import com.example.BookReview.business.model.base.Reader;
import com.example.BookReview.business.model.create.ReaderCreateModel;
import com.example.BookReview.business.service.implementation.AdministratorServiceImplementation;
import com.example.BookReview.business.service.implementation.ReaderServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reader")
public class ReaderController {


    @Autowired
    private ReaderServiceImplementation readerService;
    @Autowired
    private AdministratorServiceImplementation administratorService;


    @GetMapping("/getAll")
    public ResponseEntity<List<ReaderDTO>> findAll() {
        List<Reader> readers = readerService.findAll();

        if (readers == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(readers.stream().map(ReaderDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<ReaderDTO> findById(@PathVariable Long id) {
        Reader reader = readerService.findById(id);

        if (reader == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ReaderDTO(reader), HttpStatus.OK);
    }


    @GetMapping("/getByToken/{authenticationToken}")
    public ResponseEntity<ReaderDTO> findByAuthenticationToken(@PathVariable String authenticationToken) {
        Reader reader = readerService.findByAuthenticationToken(authenticationToken);

        if (reader == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ReaderDTO(reader), HttpStatus.OK);
    }


    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<ReaderDTO> findByEmail(@PathVariable String email) {
        Reader reader = readerService.findByEmail(email);

        if (reader == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ReaderDTO(reader), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<ReaderDTO> create(@RequestBody ReaderCreateModel createModel) {
        Reader reader = readerService.create(createModel);

        return new ResponseEntity<>(new ReaderDTO(reader), HttpStatus.OK);
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<ReaderDTO> update(@PathVariable Long id, @RequestBody ReaderCreateModel newValue, @RequestHeader("Token") String token) {
        Reader user1 = readerService.findByAuthenticationToken(token);
        Administrator user2 = administratorService.findByAuthenticationToken(token);
        if(user1 == null && user2 == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        if(user1 != null && !user1.getId().equals(id)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Reader reader = readerService.update(id, newValue);

        if (reader == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ReaderDTO(reader), HttpStatus.OK);
    }

    @PostMapping("/updateToken/{id}")
    public ResponseEntity updateAuthenticationToken(@PathVariable Long id, @RequestBody String token) {
        boolean success = readerService.updateAuthenticationToken(id, token);

        if (!success) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ReaderDTO> deleteById(@PathVariable Long id, @RequestHeader("Token") String token) {
        Reader user1 = readerService.findByAuthenticationToken(token);
        Administrator user2 = administratorService.findByAuthenticationToken(token);
        if(user1 == null && user2 == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        if(user1 != null && !user1.getId().equals(id)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Reader reader = readerService.deleteById(id);

        if (reader == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ReaderDTO(reader), HttpStatus.OK);
    }

}
