package com.example.BookReview.data.repository;

import com.example.BookReview.data.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookDB, Long> {
}


