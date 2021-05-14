package com.example.BookReview.business.service.implementation;

import com.example.BookReview.business.model.base.*;
import com.example.BookReview.business.model.create.*;
import com.example.BookReview.business.service.interfaces.*;
import com.example.BookReview.data.model.AuthorDB;
import com.example.BookReview.data.model.BookDB;
import com.example.BookReview.data.repository.AuthorRepository;
import com.example.BookReview.data.repository.BookRepository;
import com.example.BookReview.helper.BookGenre;
import com.example.BookReview.helper.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImplementation implements BookService {


    @Qualifier("bookRepository")
    @Autowired
    private BookRepository bookRepository;
    @Qualifier("authorRepository")
    @Autowired
    private AuthorRepository authorRepository;


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
    public List<Book> findAllByAuthors(List<Author> authors) {
        List<AuthorDB> authorDBS = authors.stream().map(AuthorDB::new).collect(Collectors.toList());
        return bookRepository.findAllByAuthors(authorDBS).stream().map(Book::new).collect(Collectors.toList());
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
        LocalDate publicationDate = LocalDate.of(
                createModel.getYear(),
                createModel.getMonth(),
                createModel.getDay()
        );

        List<AuthorDB> authors = new ArrayList<>();

        for(Long authorID : createModel.getAuthorIds()){
            Optional<AuthorDB> authorDB = authorRepository.findById(authorID);
            authorDB.ifPresent(authors::add);
        }

        BookDB bookDB = new BookDB(
                createModel.getTitle(),
                publicationDate,
                0,
                5.0,
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
        if (bookDB.isEmpty()){
            return null;
        }

        BookDB toUpdate= bookDB.get();

        LocalDate oldDate = toUpdate.getPublicationDate();
        int year = newValue.getYear() > 0 ? newValue.getYear() : oldDate.getYear();
        int month = newValue.getMonth() > 0 ? newValue.getMonth() : oldDate.getMonthValue();
        int day = newValue.getDay() > 0 ? newValue.getDay() : oldDate.getDayOfMonth();
        LocalDate newDate = LocalDate.of(year, month, day);
        toUpdate.setPublicationDate(newDate);

        if(newValue.getTitle() != null){
            toUpdate.setTitle(newValue.getTitle());
        }
        if(newValue.getGenre() != null){
            toUpdate.setGenre(newValue.getGenre());
        }
        if(newValue.getLanguage() != null){
            toUpdate.setLanguage(newValue.getLanguage());
        }
        if(newValue.getDescription() != null){
            toUpdate.setDescription(newValue.getDescription());
        }

        if(newValue.getAuthorIds() != null){
            List<AuthorDB> authors = new ArrayList<>();

            for(Long authorID : newValue.getAuthorIds()){
                Optional<AuthorDB> authorDB = authorRepository.findById(authorID);
                authorDB.ifPresent(authors::add);
            }
            toUpdate.setAuthors(authors);
        }

        BookDB updated = bookRepository.save(toUpdate);

        return new Book(updated);
    }

    @Override
    public Book deleteById(Long id) {
        Optional<BookDB> bookDB = bookRepository.findById(id);
        if(bookDB.isEmpty()){
            return null;
        }

        BookDB toDelete = bookDB.get();

        bookRepository.deleteById(id);
        return new Book(toDelete);
    }

    //TODO create method to update rating - observer
}

