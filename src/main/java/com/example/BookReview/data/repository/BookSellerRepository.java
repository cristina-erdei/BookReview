package com.example.BookReview.data.repository;

import com.example.BookReview.data.model.AdministratorDB;
import com.example.BookReview.data.model.BookSellerDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSellerRepository extends JpaRepository<BookSellerDB, Long> {
    BookSellerDB findByAuthenticationToken(String authenticationToken);
}
