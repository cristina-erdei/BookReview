package com.example.BookReview.data.repository;

import com.example.BookReview.data.model.BookRatingDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRatingRepository extends JpaRepository<BookRatingDB, Long> {
}
