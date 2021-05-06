package com.example.BookReview.data.repository;

import com.example.BookReview.business.model.base.BookReview;
import com.example.BookReview.data.model.BookRatingDB;
import com.example.BookReview.data.model.BookReviewDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReviewDB, Long> {
    List<BookReviewDB> findAllByBook_Id(Long book_id);

    List<BookReviewDB> findAllByReader_Id(Long reader_id);
}
