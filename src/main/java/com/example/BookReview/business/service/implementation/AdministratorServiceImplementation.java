package com.example.BookReview.business.service.implementation;

import com.example.BookReview.business.model.base.Administrator;
import com.example.BookReview.business.model.create.AdministratorCreateModel;
import com.example.BookReview.business.service.interfaces.AdministratorService;
import com.example.BookReview.data.model.AdministratorDB;
import com.example.BookReview.data.repository.AdministratorRepository;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministratorServiceImplementation implements AdministratorService {


    @Qualifier("administratorRepository")
    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public List<Administrator> findAll() {
        return administratorRepository.findAll().stream().map(Administrator::new).collect(Collectors.toList());
    }

    @Override
    public Administrator findById(Long id) {
        Optional<AdministratorDB> administratorDB = administratorRepository.findById(id);
        return administratorDB.map(Administrator::new).orElse(null);
    }

    @Override
    public Administrator findByAuthenticationToken(String authenticationToken) {
        return new Administrator(administratorRepository.findByAuthenticationToken(authenticationToken));
    }

    @Override
    public Administrator findByEmail(String email) {
        return new Administrator(administratorRepository.findByEmail(email));
    }

    @Override
    public Administrator create(AdministratorCreateModel createModel) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedPass = encoder.encodeToString(createModel.getPassword().getBytes(StandardCharsets.UTF_8));

        AdministratorDB administratorDB = new AdministratorDB(
                encodedPass,
                createModel.getEmail(),
                null,
                createModel.getName()
        );


        AdministratorDB saved = administratorRepository.save(administratorDB);

        return new Administrator(saved);
    }

    @Override
    public Administrator update(Long id, AdministratorCreateModel newValue) {
        Optional<AdministratorDB> administratorDB = administratorRepository.findById(id);
        if(administratorDB.isEmpty()){
            return null;
        }

        AdministratorDB toUpdate = administratorDB.get();

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

        AdministratorDB updated = administratorRepository.save(toUpdate);
        return new Administrator(updated);
    }

    @Override
    public boolean updateAuthenticationToken(Long id, String token) {
        Optional<AdministratorDB> administratorDB = administratorRepository.findById(id);
        if(administratorDB.isEmpty()){
            return false;
        }

        AdministratorDB toUpdate = administratorDB.get();
        toUpdate.setAuthenticationToken(token);

        administratorRepository.save(toUpdate);

        return true;
    }

    @Override
    public Administrator deleteById(Long id) {
        Optional<AdministratorDB> administratorDB = administratorRepository.findById(id);
        if(administratorDB.isEmpty()){
            return null;
        }

        AdministratorDB toDelete = administratorDB.get();

        administratorRepository.deleteById(id);
        return new Administrator(toDelete);
    }
}
