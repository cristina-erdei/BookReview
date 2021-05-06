package com.example.BookReview.data.repository;

import com.example.BookReview.data.model.BookRatingDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRatingRepository extends JpaRepository<BookRatingDB, Long> {
    List<BookRatingDB> findAllByBook_Id(Long book_id);

    List<BookRatingDB> findAllByReader_Id(Long reader_id);
}
