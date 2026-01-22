package com.hafiz.library_api_hafiz.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record BorrowerCreateRequest(
        @NotBlank String name,
        @NotBlank @Email String email
) {}
