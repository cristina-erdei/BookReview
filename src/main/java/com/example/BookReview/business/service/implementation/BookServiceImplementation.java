package com.example.BookReview.business.service.implementation;

import com.example.BookReview.business.model.DTO.BookDTO;
import com.example.BookReview.business.model.base.*;
import com.example.BookReview.business.model.create.*;
import com.example.BookReview.business.model.observer.CustomObserver;
import com.example.BookReview.business.model.strategy.*;
import com.example.BookReview.business.service.interfaces.*;
import com.example.BookReview.data.model.AuthorDB;
import com.example.BookReview.data.model.BookDB;
import com.example.BookReview.data.model.BookRatingDB;
import com.example.BookReview.data.repository.AuthorRepository;
import com.example.BookReview.data.repository.BookRatingRepository;
import com.example.BookReview.data.repository.BookRepository;
import com.example.BookReview.helper.AppConstants;
import com.example.BookReview.helper.BookGenre;
import com.example.BookReview.helper.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImplementation extends CustomObserver implements BookService {


    @Qualifier("bookRepository")
    @Autowired
    private BookRepository bookRepository;
    @Qualifier("authorRepository")
    @Autowired
    private AuthorRepository authorRepository;
    @Qualifier("bookRatingRepository")
    @Autowired
    private BookRatingRepository bookRatingRepository;

    private BookSearching bookSearching = new BookSearching(new BookTitleSearchingStrategy());


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll().stream().map(Book::new).collect(Collectors.toList());
    }

    @Override
    public Book findById(Long id) {
        Optional<BookDB> bookDB = bookRepository.findById(id);
        return bookDB.map(Book::new).orElse(null);
    }

    @Override
    public List<Book> findAllByGenre(BookGenre genre) {
        return bookRepository.findAllByGenre(genre).stream().map(Book::new).collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByLanguage(Language language) {
        return bookRepository.findAllByLanguage(language).stream().map(Book::new).collect(Collectors.toList());
    }

    @Override
    public Book create(BookCreateModel createModel) {
        if (createModel.getYear() < AppConstants.minimumDateYear ||
                createModel.getMonth() < AppConstants.minimumDateMonth || createModel.getMonth() > AppConstants.maximumDateMonth ||
                createModel.getDay() < AppConstants.minimumDateDay || createModel.getDay() > AppConstants.maximumDateDay) {
            return null;
        }

        LocalDate publicationDate = LocalDate.of(
                createModel.getYear(),
                createModel.getMonth(),
                createModel.getDay()
        );

        List<AuthorDB> authors = new ArrayList<>();

        for (Long authorID : createModel.getAuthorIds()) {
            Optional<AuthorDB> authorDB = authorRepository.findById(authorID);
            authorDB.ifPresent(authors::add);
        }

        BookDB bookDB = new BookDB(
                createModel.getTitle(),
                publicationDate,
                AppConstants.defaultNumberOfInitialRatings,
                AppConstants.defaultBookRating,
                createModel.getGenre(),
                createModel.getLanguage(),
                createModel.getDescription(),
                authors
        );

        BookDB saved = bookRepository.save(bookDB);
        return new Book(saved);
    }

    @Override
    public Book update(Long id, BookCreateModel newValue) {
        Optional<BookDB> bookDB = bookRepository.findById(id);
        if (bookDB.isEmpty()) {
            return null;
        }

        BookDB toUpdate = bookDB.get();

        LocalDate oldDate = toUpdate.getPublicationDate();
        int year = newValue.getYear() > AppConstants.defaultYear ? newValue.getYear() : oldDate.getYear();
        int month = newValue.getMonth() > AppConstants.defaultMonth ? newValue.getMonth() : oldDate.getMonthValue();
        int day = newValue.getDay() > AppConstants.defaultDay ? newValue.getDay() : oldDate.getDayOfMonth();
        LocalDate newDate = LocalDate.of(year, month, day);
        toUpdate.setPublicationDate(newDate);

        if (newValue.getTitle() != null) {
            toUpdate.setTitle(newValue.getTitle());
        }
        if (newValue.getGenre() != null) {
            toUpdate.setGenre(newValue.getGenre());
        }
        if (newValue.getLanguage() != null) {
            toUpdate.setLanguage(newValue.getLanguage());
        }
        if (newValue.getDescription() != null) {
            toUpdate.setDescription(newValue.getDescription());
        }

        if (newValue.getAuthorIds() != null) {
            List<AuthorDB> authors = new ArrayList<>();

            for (Long authorID : newValue.getAuthorIds()) {
                Optional<AuthorDB> authorDB = authorRepository.findById(authorID);
                authorDB.ifPresent(authors::add);
            }
            toUpdate.setAuthors(authors);
        }

        BookDB updated = bookRepository.save(toUpdate);

        return new Book(updated);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<BookDB> bookDB = bookRepository.findById(id);
        if (bookDB.isEmpty()) {
            return false;
        }

        bookRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Book> search(SearchRequestModel model) {
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
                return null;
        }

        List<Book> books = findAll();
        List<Book> searchResult;
        if (books == null) {
            return null;
        }
        try {
            searchResult = bookSearching.findBooks(books, model.getData());
        } catch (Exception e) {
            return null;
        }

        return searchResult;

    }


    //observable pattern -> Observer
    public void updateObserver(Long id) {
        Optional<BookDB> bookDB = bookRepository.findById(id);
        if (bookDB.isEmpty()) {
            return;
        }

        BookDB book = bookDB.get();

        List<BookRatingDB> bookRatingDBS = bookRatingRepository.findAllByBook_Id(id);
        int totalNumber = bookRatingDBS.size();

        if (totalNumber == 0) {
            book.setMeanRating(AppConstants.defaultBookRating);
            book.setTotalNumberOfRatings(AppConstants.defaultNumberOfInitialRatings);
        } else {
            double rating = 0;
            for (BookRatingDB ratingDB : bookRatingDBS) {
                rating += ratingDB.getRating();
            }
            rating /= totalNumber;

            book.setMeanRating(rating);
            book.setTotalNumberOfRatings(totalNumber);
        }
        bookRepository.save(book);
    }

}

