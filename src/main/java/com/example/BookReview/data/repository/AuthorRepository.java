package com.example.BookReview.data.repository;

import com.example.BookReview.data.model.AuthorDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorDB, Long> {
}
