package com.hafiz.library_api_hafiz;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hafiz.library_api_hafiz.entity.Book;
import com.hafiz.library_api_hafiz.entity.Borrower;
import com.hafiz.library_api_hafiz.exception.ConflictException;
import com.hafiz.library_api_hafiz.repository.BookRepository;
import com.hafiz.library_api_hafiz.repository.BorrowerRepository;
import com.hafiz.library_api_hafiz.service.BorrowService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class BorrowServiceTest {

  @Autowired BorrowService borrowService;
  @Autowired BorrowerRepository borrowerRepository;
  @Autowired BookRepository bookRepository;

  @Test
  void borrow_same_book_twice_should_conflict() {
    Borrower b1 = borrowerRepository.save(Borrower.builder().name("A").email("a@a.com").build());
    Borrower b2 = borrowerRepository.save(Borrower.builder().name("B").email("b@b.com").build());

    Book book = bookRepository.save(Book.builder().isbn("X").title("T").author("AU").build());

    borrowService.borrowBook(b1.getId(), book.getId());

    assertThrows(ConflictException.class, () -> borrowService.borrowBook(b2.getId(), book.getId()));
  }
}

