package com.hafiz.library_api_hafiz.dto;

public record BookResponse(
        Long id,
        String isbn,
        String title,
        String author,
        boolean available,
        Long borrowedByBorrowerId
) {}
