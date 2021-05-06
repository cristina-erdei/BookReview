package com.example.BookReview.data.repository;

import com.example.BookReview.data.model.BookReviewDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReviewDB, Long> {
}
