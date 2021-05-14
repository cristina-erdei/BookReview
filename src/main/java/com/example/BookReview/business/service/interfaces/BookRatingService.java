package com.example.BookReview.business.service.interfaces;

import com.example.BookReview.business.model.base.Book;
import com.example.BookReview.business.model.base.BookRating;
import com.example.BookReview.business.model.create.BookCreateModel;
import com.example.BookReview.business.model.create.BookRatingCreateModel;
import com.example.BookReview.data.model.BookRatingDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookRatingService {
    List<BookRating> findAll();

    BookRating findById(Long id);

    List<BookRating> findAllByBook_Id(Long book_id);

    List<BookRating> findAllByReader_Id(Long reader_id);

    BookRating create(BookRatingCreateModel createModel);

    BookRating update(Long id, BookRatingCreateModel newValue);

    BookRating deleteById(Long id);

}
