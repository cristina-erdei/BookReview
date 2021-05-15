package com.example.BookReview.business.service.implementation;

import com.example.BookReview.business.model.base.Author;
import com.example.BookReview.business.model.base.Author;
import com.example.BookReview.business.model.create.AuthorCreateModel;
import com.example.BookReview.business.service.interfaces.AuthorService;
import com.example.BookReview.data.model.AuthorDB;
import com.example.BookReview.data.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImplementation implements AuthorService {


    @Qualifier("authorRepository")
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll().stream().map(Author::new).collect(Collectors.toList());
    }

    @Override
    public Author findById(Long id) {
        Optional<AuthorDB> authorDB = authorRepository.findById(id);
        return authorDB.map(Author::new).orElse(null);
    }

    @Override
    public Author create(AuthorCreateModel createModel) {
        LocalDate dateOfBirth;

        if (createModel.getBirthYear() == 0) {
            return null;
        }

        dateOfBirth = LocalDate.of(
                createModel.getBirthYear(),
                createModel.getBirthMonth(),
                createModel.getBirthDay()
        );


        LocalDate dateOfDeath;

        if (createModel.getDeathYear() == 0) {
            dateOfDeath = null;
        } else {
            dateOfDeath = LocalDate.of(
                    createModel.getDeathYear(),
                    createModel.getDeathMonth(),
                    createModel.getDeathDay()
            );
        }

        AuthorDB authorDB = new AuthorDB(
                createModel.getFullName(),
                dateOfBirth,
                dateOfDeath
        );

        AuthorDB saved = authorRepository.save(authorDB);

        return new Author(saved);
    }

    @Override
    public Author update(Long id, AuthorCreateModel newValue) {
        Optional<AuthorDB> authorDB = authorRepository.findById(id);
        if (authorDB.isEmpty()) {
            return null;
        }

        AuthorDB toUpdate = authorDB.get();

        LocalDate oldBirthDate = toUpdate.getDateOfBirth();
        int year = newValue.getBirthYear() > 0 ? newValue.getBirthYear() : oldBirthDate.getYear();
        int month = newValue.getBirthMonth() > 0 ? newValue.getBirthMonth() : oldBirthDate.getMonthValue();
        int day = newValue.getBirthDay() > 0 ? newValue.getBirthDay() : oldBirthDate.getDayOfMonth();
        LocalDate newBirthDate = LocalDate.of(year, month, day);
        toUpdate.setDateOfBirth(newBirthDate);


        LocalDate oldDeathDate = toUpdate.getDateOfDeath();
        LocalDate newDeathDate;
        if (oldDeathDate == null) {
            if (newValue.getDeathYear() == 0) {
                newDeathDate = null;
            } else if (newValue.getDeathDay() == 0 || newValue.getDeathMonth() == 0) {
                return null;
            } else {
                newDeathDate = LocalDate.of(newValue.getDeathYear(), newValue.getDeathMonth(), newValue.getDeathDay());
            }
        } else {
            year = newValue.getDeathYear() > 0 ? newValue.getDeathYear() : oldDeathDate.getYear();
            month = newValue.getDeathMonth() > 0 ? newValue.getDeathMonth() : oldDeathDate.getMonthValue();
            day = newValue.getDeathDay() > 0 ? newValue.getDeathDay() : oldDeathDate.getDayOfMonth();
            newDeathDate = LocalDate.of(year, month, day);
        }
        toUpdate.setDateOfDeath(newDeathDate);

        if (newValue.getFullName() != null) {
            toUpdate.setFullName(newValue.getFullName());
        }

        AuthorDB updated = authorRepository.save(toUpdate);

        return new Author(updated);
    }

    @Override
    public Author deleteById(Long id) {
        Optional<AuthorDB> authorDB = authorRepository.findById(id);
        if (authorDB.isEmpty()) {
            return null;
        }

        AuthorDB toDelete = authorDB.get();

        authorRepository.deleteById(id);
        return new Author(toDelete);
    }
}
