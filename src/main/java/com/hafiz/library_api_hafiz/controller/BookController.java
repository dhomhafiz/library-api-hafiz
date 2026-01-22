package com.hafiz.library_api_hafiz.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.hafiz.library_api_hafiz.dto.BookCreateRequest;
import com.hafiz.library_api_hafiz.dto.BookResponse;
import com.hafiz.library_api_hafiz.entity.Book;
import com.hafiz.library_api_hafiz.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book register(@Valid @RequestBody BookCreateRequest req) {
        return bookService.register(req);
    }

    @GetMapping
    public List<BookResponse> listAll() {
        return bookService.listAll();
    }
}
