package com.example.BookReview.data.repository;

import com.example.BookReview.data.model.AdministratorDB;
import com.example.BookReview.data.model.ReaderDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<ReaderDB, Long> {
    ReaderDB findByAuthenticationToken(String authenticationToken);
}
