package com.library.library.service;

import com.library.library.dto.Book.BookDto;
import com.library.library.dto.Book.CreateBookDto;

import java.util.List;

public interface BookService {

    BookDto createBook(CreateBookDto createBookDto, int authorId);

    BookDto getBookById(int bookId);

    List<BookDto> getAllBooks();

    List<BookDto> getBooksByAuthor(int authorId);

    List<BookDto> getBooksByAuthorName(String authorName);

    List<BookDto> getBooksByGenre(String genre);

    List<BookDto> getBooksByTitle(String title);

    List<BookDto> getBooksAvailable();

    void softDeleteBook(int bookId);

}
