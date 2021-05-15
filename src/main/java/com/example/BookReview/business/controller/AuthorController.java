package com.example.BookReview.business.controller;

import com.example.BookReview.business.model.DTO.AdministratorDTO;
import com.example.BookReview.business.model.DTO.AuthorDTO;
import com.example.BookReview.business.model.base.Administrator;
import com.example.BookReview.business.model.base.Author;
import com.example.BookReview.business.model.create.AuthorCreateModel;
import com.example.BookReview.business.service.implementation.AuthorServiceImplementation;
import com.example.BookReview.business.service.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {


    @Autowired
    private AuthorServiceImplementation authorService;

    @GetMapping("/getAll")
    public ResponseEntity<List<AuthorDTO>> findAll() {
        List<Author> authors = authorService.findAll();

        if (authors == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(authors.stream().map(AuthorDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable Long id) {
        Author author = authorService.findById(id);

        if (author == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new AuthorDTO(author), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorCreateModel createModel) {
        Author author = authorService.create(createModel);

        return new ResponseEntity<>(new AuthorDTO(author), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @RequestBody AuthorCreateModel newValue) {
        Author author = authorService.update(id, newValue);

        if (author == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new AuthorDTO(author), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<AuthorDTO> deleteById(@PathVariable Long id) {
        Author author = authorService.deleteById(id);

        if (author == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new AuthorDTO(author), HttpStatus.OK);
    }

}
