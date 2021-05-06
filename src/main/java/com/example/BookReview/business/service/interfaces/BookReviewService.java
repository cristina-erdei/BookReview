package com.example.BookReview.business.service.interfaces;

import com.example.BookReview.business.model.base.Book;
import com.example.BookReview.business.model.base.BookReview;
import com.example.BookReview.business.model.create.BookCreateModel;
import com.example.BookReview.business.model.create.BookReviewCreateModel;
import com.example.BookReview.data.model.BookRatingDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookReviewService {
    List<BookReview> findAll();

    BookReview findById(Long id);

    List<BookReview> findAllByBook_Id(Long book_id);

    List<BookReview> findAllByReader_Id(Long reader_id);

    List<BookReview> findAllByBook_Id(Long book_id);

    List<BookReview> findAllByReader_Id(Long reader_id);

    BookReview create(BookReviewCreateModel createModel);

    BookReview update(Long id, BookReviewCreateModel newValue);

    BookReview deleteById(Long id);

}
