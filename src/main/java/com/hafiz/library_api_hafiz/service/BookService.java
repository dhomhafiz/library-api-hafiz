package com.hafiz.library_api_hafiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.hafiz.library_api_hafiz.dto.BookCreateRequest;
import com.hafiz.library_api_hafiz.dto.BookResponse;
import com.hafiz.library_api_hafiz.entity.Book;
import com.hafiz.library_api_hafiz.exception.BadRequestException;
import com.hafiz.library_api_hafiz.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book register(BookCreateRequest req) {
        // ISBN consistency rule:
        bookRepository.findFirstByIsbn(req.isbn()).ifPresent(existing -> {
            boolean sameTitle = existing.getTitle().equals(req.title());
            boolean sameAuthor = existing.getAuthor().equals(req.author());
            if (!sameTitle || !sameAuthor) {
                throw new BadRequestException(
                        "ISBN already exists with different title/author. ISBN must map to the same title & author."
                );
            }
        });

        // Multiple copies allowed => always create new row with new id
        Book book = Book.builder()
                .isbn(req.isbn())
                .title(req.title())
                .author(req.author())
                .borrowedBy(null)
                .build();

        return bookRepository.save(book);
    }

    public List<BookResponse> listAll() {
        return bookRepository.findAll().stream()
                .map(b -> new BookResponse(
                        b.getId(),
                        b.getIsbn(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getBorrowedBy() == null,
                        b.getBorrowedBy() == null ? null : b.getBorrowedBy().getId()
                ))
                .toList();
    }
}
