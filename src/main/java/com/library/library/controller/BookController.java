package com.library.library.controller;

import com.library.library.dto.Book.BookDto;
import com.library.library.dto.Book.CreateBookDto;
import com.library.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only.")
    @PostMapping("/create/{authorId}")
    public ResponseEntity<BookDto> createBook(@RequestBody CreateBookDto createBookDto,
                                              @PathVariable("authorId") int authorId) {
        BookDto createdBook = bookService.createBook(createBookDto, authorId);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/get/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable(value = "bookId") int bookId) {
        BookDto book = bookService.getBookById(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable(value = "authorId") int authorId) {
        List<BookDto> books = bookService.getBooksByAuthor(authorId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/author/name/{authorName}")
    public ResponseEntity<List<BookDto>> getBooksByAuthorName(@PathVariable String authorName) {
        List<BookDto> books = bookService.getBooksByAuthorName(authorName);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookDto>> getBooksByGenre(@PathVariable String genre) {
        List<BookDto> books = bookService.getBooksByGenre(genre);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookDto>> getBooksByTitle(@PathVariable String title) {
        List<BookDto> books = bookService.getBooksByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/get/all")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/available/get/all")
    public ResponseEntity<List<BookDto>> getBooksAvailable() {
        List<BookDto> books = bookService.getBooksAvailable();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only.")
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity softDeleteBook(@PathVariable("bookId") int bookId) {
        bookService.softDeleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


