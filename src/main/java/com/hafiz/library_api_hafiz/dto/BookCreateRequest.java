package com.hafiz.library_api_hafiz.dto;

import jakarta.validation.constraints.NotBlank;

public record BookCreateRequest(
        @NotBlank String isbn,
        @NotBlank String title,
        @NotBlank String author
) {}
