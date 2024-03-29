package com.example.BookReview.business.service.implementation;

import com.example.BookReview.business.model.base.Reader;
import com.example.BookReview.business.model.create.ReaderCreateModel;
import com.example.BookReview.business.service.interfaces.ReaderService;
import com.example.BookReview.data.model.ReaderDB;
import com.example.BookReview.data.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReaderServiceImplementation implements ReaderService {


    @Qualifier("readerRepository")
    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public List<Reader> findAll() {
        return readerRepository.findAll().stream().map(Reader::new).collect(Collectors.toList());
    }

    @Override
    public Reader findById(Long id) {
        Optional<ReaderDB> readerDB = readerRepository.findById(id);
        return readerDB.map(Reader::new).orElse(null);
    }

    @Override
    public Reader findByAuthenticationToken(String authenticationToken) {
        ReaderDB readerDB = readerRepository.findByAuthenticationToken(authenticationToken);
        if(readerDB == null) {
            return null;
        }
        return new Reader(readerDB);
    }

    @Override
    public Reader findByEmail(String email) {
        ReaderDB readerDB = readerRepository.findByEmail(email);
        if(readerDB == null) {
            return null;
        }
        return new Reader(readerDB);
    }

    @Override
    public Reader create(ReaderCreateModel createModel) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedPass = encoder.encodeToString(createModel.getPassword().getBytes(StandardCharsets.UTF_8));

        ReaderDB old = readerRepository.findByEmail(createModel.getEmail());
        if(old != null) { //cannot have 2 users with same email
            return null;
        }
        ReaderDB readerDB = new ReaderDB(
                encodedPass,
                createModel.getEmail(),
                null,
                createModel.getName()
        );


        ReaderDB saved = readerRepository.save(readerDB);

        return new Reader(saved);
    }

    @Override
    public Reader update(Long id, ReaderCreateModel newValue) {
        Optional<ReaderDB> readerDB = readerRepository.findById(id);
        if(readerDB.isEmpty()){
            return null;
        }

        ReaderDB toUpdate = readerDB.get();

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

        ReaderDB updated = readerRepository.save(toUpdate);
        return new Reader(updated);
    }

    @Override
    public boolean updateAuthenticationToken(Long id, String token) {
        Optional<ReaderDB> readerDB = readerRepository.findById(id);
        if(readerDB.isEmpty()){
            return false;
        }

        ReaderDB toUpdate = readerDB.get();
        toUpdate.setAuthenticationToken(token);

        readerRepository.save(toUpdate);

        return true;
    }

    @Override
    public Reader deleteById(Long id) {
        Optional<ReaderDB> readerDB = readerRepository.findById(id);
        if(readerDB.isEmpty()){
            return null;
        }

        ReaderDB toDelete = readerDB.get();

        readerRepository.deleteById(id);
        return new Reader(toDelete);
    }
}
