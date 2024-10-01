package com.library.library.controller;

import com.library.library.dto.Book.BookDto;
import com.library.library.dto.Borrow.BorrowDto;
import com.library.library.service.BorrowService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {
    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only.")
    @PostMapping("/create/{bookId}/{userId}")
    public ResponseEntity<BorrowDto> createBorrow(@PathVariable("bookId") int bookId,
                                                  @PathVariable("userId") int userId) {
        BorrowDto createdBorrow = borrowService.createBorrow(bookId, userId);
        return new ResponseEntity<>(createdBorrow, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only.")
    @GetMapping("/get/{borrowId}")
    public ResponseEntity<BorrowDto> getBorrowById(@PathVariable(value = "borrowId") int borrowId) {
        BorrowDto borrow = borrowService.getBorrowById(borrowId);
        return new ResponseEntity<>(borrow, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only. Returns the list of books that a specific User have borrowed in his life.")
    @GetMapping("/user/{userId}/books")
    public ResponseEntity<List<BookDto>> getBooksBorrowedByUserId(@PathVariable(value = "userId") int userId) {
        List<BookDto> books = borrowService.getBooksBorrowedByUserId(userId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only.")
    @GetMapping("/get/all/history")
    public ResponseEntity<List<BorrowDto>> getAllBorrowsHistory() {
        List<BorrowDto> borrows = borrowService.getAllBorrowsHistory();
        return new ResponseEntity<>(borrows, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only.")
    @PutMapping("/return/{borrowId}")
    public ResponseEntity<String> returnBorrowedBook(@Valid @PathVariable("borrowId") int borrowId) {
        borrowService.returnBorrowedBook(borrowId);
        return ResponseEntity.ok("Book successfully returned.");
    }
}
