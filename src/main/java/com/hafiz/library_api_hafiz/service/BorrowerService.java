package com.hafiz.library_api_hafiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.hafiz.library_api_hafiz.dto.BorrowerCreateRequest;
import com.hafiz.library_api_hafiz.entity.Borrower;
import com.hafiz.library_api_hafiz.exception.ConflictException;
import com.hafiz.library_api_hafiz.exception.NotFoundException;
import com.hafiz.library_api_hafiz.repository.BorrowerRepository;

@Service
@RequiredArgsConstructor
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    public Borrower register(BorrowerCreateRequest req) {
        borrowerRepository.findByEmail(req.email()).ifPresent(b -> {
            throw new ConflictException("Email already registered: " + req.email());
        });

        Borrower borrower = Borrower.builder()
                .name(req.name())
                .email(req.email())
                .build();

        return borrowerRepository.save(borrower);
    }

    public Borrower get(Long id) {
        return borrowerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Borrower not found: " + id));
    }
}
