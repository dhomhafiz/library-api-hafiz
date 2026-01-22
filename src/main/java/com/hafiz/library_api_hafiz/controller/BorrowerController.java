package com.hafiz.library_api_hafiz.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.hafiz.library_api_hafiz.dto.BorrowerCreateRequest;
import com.hafiz.library_api_hafiz.entity.Borrower;
import com.hafiz.library_api_hafiz.service.BorrowerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/borrowers")
public class BorrowerController {

    private final BorrowerService borrowerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Borrower register(@Valid @RequestBody BorrowerCreateRequest req) {
        return borrowerService.register(req);
    }

    @GetMapping("/{id}")
    public Borrower get(@PathVariable Long id) {
        return borrowerService.get(id);
    }
}

