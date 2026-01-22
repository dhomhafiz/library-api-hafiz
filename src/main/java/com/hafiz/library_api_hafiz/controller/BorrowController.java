package com.hafiz.library_api_hafiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.hafiz.library_api_hafiz.service.BorrowService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/borrowers/{borrowerId}")
public class BorrowController {

    private final BorrowService borrowService;

    @PostMapping("/borrow/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrow(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        borrowService.borrowBook(borrowerId, bookId);
    }

    @PostMapping("/return/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        borrowService.returnBook(borrowerId, bookId);
    }
}

