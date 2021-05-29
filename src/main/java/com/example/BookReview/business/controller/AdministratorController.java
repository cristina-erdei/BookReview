package com.example.BookReview.business.controller;

import com.example.BookReview.business.model.DTO.AdministratorDTO;
import com.example.BookReview.business.model.base.Administrator;
import com.example.BookReview.business.model.create.AdministratorCreateModel;
import com.example.BookReview.business.service.implementation.AdministratorServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {


    @Autowired
    private AdministratorServiceImplementation administratorService;

    @GetMapping("/getAll")
    public ResponseEntity<List<AdministratorDTO>> findAll() {
        List<Administrator> administrators = administratorService.findAll();

        if (administrators == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(administrators.stream().map(AdministratorDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<AdministratorDTO> findById(@PathVariable Long id) {
        Administrator administrator = administratorService.findById(id);

        if (administrator == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new AdministratorDTO(administrator), HttpStatus.OK);
    }


    @GetMapping("/getByToken/{authenticationToken}")
    public ResponseEntity<AdministratorDTO> findByAuthenticationToken(@PathVariable String authenticationToken) {
        Administrator administrator = administratorService.findByAuthenticationToken(authenticationToken);

        if (administrator == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new AdministratorDTO(administrator), HttpStatus.OK);
    }


    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<AdministratorDTO> findByEmail(@PathVariable String email) {
        Administrator administrator = administratorService.findByEmail(email);

        if (administrator == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new AdministratorDTO(administrator), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<AdministratorDTO> create(@RequestBody AdministratorCreateModel createModel) {
        Administrator administrator = administratorService.create(createModel);

        return new ResponseEntity<>(new AdministratorDTO(administrator), HttpStatus.OK);
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<AdministratorDTO> update(@PathVariable Long id, @RequestBody AdministratorCreateModel newValue,@RequestHeader("Token") String token) {
        Administrator user = administratorService.findByAuthenticationToken(token);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Administrator administrator = administratorService.update(id, newValue);

        if (administrator == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new AdministratorDTO(administrator), HttpStatus.OK);
    }

    @PostMapping("/updateToken/{id}")
    public ResponseEntity updateAuthenticationToken(@PathVariable Long id, @RequestBody String token) {
        boolean success = administratorService.updateAuthenticationToken(id, token);

        if (!success) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<AdministratorDTO> deleteById(@PathVariable Long id, @RequestHeader("Token") String token) {
        Administrator user = administratorService.findByAuthenticationToken(token);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Administrator administrator = administratorService.deleteById(id);

        if (administrator == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new AdministratorDTO(administrator), HttpStatus.OK);
    }

}
