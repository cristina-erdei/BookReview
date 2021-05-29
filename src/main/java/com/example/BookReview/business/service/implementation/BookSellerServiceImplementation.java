package com.example.BookReview.business.service.implementation;

import com.example.BookReview.business.model.base.BookSeller;
import com.example.BookReview.business.model.create.BookSellerCreateModel;
import com.example.BookReview.business.service.interfaces.BookSellerService;
import com.example.BookReview.data.model.BookSellerDB;
import com.example.BookReview.data.repository.BookSellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookSellerServiceImplementation implements BookSellerService {


    @Qualifier("bookSellerRepository")
    @Autowired
    private BookSellerRepository bookSellerRepository;

    @Override
    public List<BookSeller> findAll() {
        return bookSellerRepository.findAll().stream().map(BookSeller::new).collect(Collectors.toList());
    }

    @Override
    public BookSeller findById(Long id) {
        Optional<BookSellerDB> bookSellerDB = bookSellerRepository.findById(id);
        return bookSellerDB.map(BookSeller::new).orElse(null);
    }

    @Override
    public BookSeller findByAuthenticationToken(String authenticationToken) {
        BookSellerDB bookSellerDB = bookSellerRepository.findByAuthenticationToken(authenticationToken);
        if(bookSellerDB == null) {
            return null;
        }
        return new BookSeller(bookSellerDB);
    }

    @Override
    public BookSeller findByEmail(String email) {
        BookSellerDB bookSellerDB = bookSellerRepository.findByEmail(email);
        if(bookSellerDB == null) {
            return null;
        }
        return new BookSeller(bookSellerDB);
    }

    @Override
    public BookSeller create(BookSellerCreateModel createModel) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedPass = encoder.encodeToString(createModel.getPassword().getBytes(StandardCharsets.UTF_8));

        BookSellerDB bookSellerDB = new BookSellerDB(
                encodedPass,
                createModel.getEmail(),
                null,
                createModel.getName()
        );


        BookSellerDB saved = bookSellerRepository.save(bookSellerDB);

        return new BookSeller(saved);
    }

    @Override
    public BookSeller update(Long id, BookSellerCreateModel newValue) {
        Optional<BookSellerDB> bookSellerDB = bookSellerRepository.findById(id);
        if(bookSellerDB.isEmpty()){
            return null;
        }

        BookSellerDB toUpdate = bookSellerDB.get();

        if(newValue.getPassword() != null){
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedPass = encoder.encodeToString(newValue.getPassword().getBytes(StandardCharsets.UTF_8));

            toUpdate.setPassword(encodedPass);
        }

        if (newValue.getEmail() != null){
            toUpdate.setPassword(newValue.getEmail());
        }

        if (newValue.getName() != null){
            toUpdate.setName(newValue.getName());
        }

        BookSellerDB updated = bookSellerRepository.save(toUpdate);
        return new BookSeller(updated);
    }

    @Override
    public boolean updateAuthenticationToken(Long id, String token) {
        Optional<BookSellerDB> bookSellerDB = bookSellerRepository.findById(id);
        if(bookSellerDB.isEmpty()){
            return false;
        }

        BookSellerDB toUpdate = bookSellerDB.get();
        toUpdate.setAuthenticationToken(token);

        bookSellerRepository.save(toUpdate);

        return true;
    }

    @Override
    public BookSeller deleteById(Long id) {
        Optional<BookSellerDB> bookSellerDB = bookSellerRepository.findById(id);
        if(bookSellerDB.isEmpty()){
            return null;
        }

        BookSellerDB toDelete = bookSellerDB.get();

        bookSellerRepository.deleteById(id);
        return new BookSeller(toDelete);
    }
}
