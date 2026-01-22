package com.hafiz.library_api_hafiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafiz.library_api_hafiz.entity.Book;
import com.hafiz.library_api_hafiz.entity.Borrower;
import com.hafiz.library_api_hafiz.exception.ConflictException;
import com.hafiz.library_api_hafiz.exception.NotFoundException;
import com.hafiz.library_api_hafiz.repository.BookRepository;
import com.hafiz.library_api_hafiz.repository.BorrowerRepository;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    @Transactional
    public void borrowBook(Long borrowerId, Long bookId) {
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new NotFoundException("Borrower not found: " + borrowerId));

        // Lock the book row to prevent concurrent borrows
        Book book = bookRepository.findByIdForUpdate(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found: " + bookId));

        if (book.getBorrowedBy() != null) {
            throw new ConflictException("Book is already borrowed: " + bookId);
        }

        book.setBorrowedBy(borrower);
        // JPA will flush on commit
    }

    @Transactional
    public void returnBook(Long borrowerId, Long bookId) {
        borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new NotFoundException("Borrower not found: " + borrowerId));

        Book book = bookRepository.findByIdForUpdate(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found: " + bookId));

        if (book.getBorrowedBy() == null) {
            throw new ConflictException("Book is not currently borrowed: " + bookId);
        }

        // Optional strict rule: only same borrower can return
        if (!book.getBorrowedBy().getId().equals(borrowerId)) {
            throw new ConflictException("This borrower didn't borrow the book. borrowerId=" + borrowerId);
        }

        book.setBorrowedBy(null);
    }
}

