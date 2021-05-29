package com.example.BookReview.business.service.implementation;

import com.example.BookReview.business.model.base.Author;
import com.example.BookReview.business.model.create.AuthorCreateModel;
import com.example.BookReview.business.service.interfaces.AuthorService;
import com.example.BookReview.data.model.AuthorDB;
import com.example.BookReview.data.repository.AuthorRepository;
import com.example.BookReview.helper.AppConstants;
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

        if (createModel.getBirthYear() < AppConstants.minimumDateYear ||
                createModel.getBirthMonth() < AppConstants.minimumDateMonth || createModel.getBirthMonth() > AppConstants.maximumDateMonth ||
                createModel.getBirthDay() < AppConstants.minimumDateDay || createModel.getBirthDay() > AppConstants.maximumDateDay) {
            return null;
        }

        dateOfBirth = LocalDate.of(
                createModel.getBirthYear(),
                createModel.getBirthMonth(),
                createModel.getBirthDay()
        );


        LocalDate dateOfDeath;

        if (createModel.getDeathYear() == AppConstants.defaultYear) {
            dateOfDeath = null;
        } else if(createModel.getDeathYear() >= AppConstants.minimumDateYear ||
                createModel.getDeathMonth() >= AppConstants.minimumDateMonth || createModel.getDeathMonth() <= AppConstants.maximumDateMonth ||
                createModel.getDeathDay() >= AppConstants.minimumDateDay || createModel.getDeathDay() <= AppConstants.maximumDateDay)
            {
            dateOfDeath = LocalDate.of(
                    createModel.getDeathYear(),
                    createModel.getDeathMonth(),
                    createModel.getDeathDay()
            );
        } else {
            dateOfDeath = null;
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
        int year = newValue.getBirthYear() > AppConstants.defaultYear ? newValue.getBirthYear() : oldBirthDate.getYear();
        int month = newValue.getBirthMonth() > AppConstants.defaultMonth ? newValue.getBirthMonth() : oldBirthDate.getMonthValue();
        int day = newValue.getBirthDay() > AppConstants.defaultDay ? newValue.getBirthDay() : oldBirthDate.getDayOfMonth();
        LocalDate newBirthDate = LocalDate.of(year, month, day);
        toUpdate.setDateOfBirth(newBirthDate);


        LocalDate oldDeathDate = toUpdate.getDateOfDeath();
        LocalDate newDeathDate;
        if (oldDeathDate == null) {
            if (newValue.getDeathYear() == AppConstants.defaultYear) {
                newDeathDate = null;
            } else if (
                    newValue.getDeathMonth() < AppConstants.minimumDateMonth || newValue.getDeathMonth() > AppConstants.maximumDateMonth ||
                    newValue.getDeathDay() < AppConstants.minimumDateDay || newValue.getDeathDay() > AppConstants.maximumDateDay
            ) {
                return null;
            } else {
                newDeathDate = LocalDate.of(newValue.getDeathYear(), newValue.getDeathMonth(), newValue.getDeathDay());
            }
        } else {
            year = newValue.getDeathYear() > AppConstants.defaultYear ? newValue.getDeathYear() : oldDeathDate.getYear();
            month = newValue.getDeathMonth() > AppConstants.defaultMonth ? newValue.getDeathMonth() : oldDeathDate.getMonthValue();
            day = newValue.getDeathDay() > AppConstants.defaultDay ? newValue.getDeathDay() : oldDeathDate.getDayOfMonth();
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
