package com.example.BookReview.data.repository;

import com.example.BookReview.data.model.AdministratorDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<AdministratorDB, Long> {
    AdministratorDB findByAuthenticationToken(String authenticationToken);
    AdministratorDB findByEmail(String email);
}
