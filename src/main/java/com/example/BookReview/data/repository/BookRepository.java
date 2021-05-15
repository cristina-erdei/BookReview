package com.example.BookReview.data.repository;

import com.example.BookReview.business.model.base.Author;
import com.example.BookReview.business.model.base.Book;
import com.example.BookReview.data.model.*;
import com.example.BookReview.helper.BookGenre;
import com.example.BookReview.helper.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookDB, Long> {
     List<BookDB> findAllByGenre(BookGenre genre);

    List<BookDB> findAllByLanguage(Language language);

}


