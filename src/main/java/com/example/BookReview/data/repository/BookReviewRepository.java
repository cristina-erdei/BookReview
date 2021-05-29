package com.example.BookReview.data.repository;

import com.example.BookReview.data.model.BookReviewDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReviewDB, Long> {
    List<BookReviewDB> findAllByBook_Id(Long book_id);

    List<BookReviewDB> findAllByReader_Id(Long reader_id);
}
