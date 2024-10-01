package com.library.library.controller;

import com.library.library.dto.Author.AuthorDto;
import com.library.library.dto.Author.CreateAuthorDto;
import com.library.library.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "ADMIN only.")
    @PostMapping("/create")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody CreateAuthorDto createAuthorDto) {
        AuthorDto createdAuthor = authorService.createAuthor(createAuthorDto);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/get/{authorId}")
    public ResponseEntity<AuthorDto> getBook(@PathVariable(value = "authorId") int authorId) {
        AuthorDto author = authorService.getAuthorById(authorId);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/get/all")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<AuthorDto> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}

