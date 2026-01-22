package com.hafiz.library_api_hafiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hafiz.library_api_hafiz.entity.Borrower;

import java.util.Optional;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    Optional<Borrower> findByEmail(String email);
}
