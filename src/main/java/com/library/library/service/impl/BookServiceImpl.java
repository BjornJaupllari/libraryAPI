package com.library.library.service.impl;

import com.library.library.dto.Book.BookDto;
import com.library.library.dto.Book.CreateBookDto;
import com.library.library.entity.Author;
import com.library.library.entity.Book;
import com.library.library.exeption.GlobalException;
import com.library.library.mapper.BookMapper;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
import com.library.library.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookDto createBook(CreateBookDto createBookDto, int authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with ID " + authorId + " not found"));

        Book book = bookMapper.dtoToEntity(createBookDto);
        System.out.println("Mapped Book Entity: " + book.toString());

        book.setAuthor(author);
        book.setCreatedAt(new Date());

        Book savedBook = bookRepository.save(book);
        return bookMapper.entityToDto(savedBook);
    }

    @Override
    public BookDto getBookById(int bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new GlobalException("Book with ID " + bookId + " not found"));

        return bookMapper.entityToDto(book);
    }

    @Override
    public List<BookDto> getBooksByAuthor(int authorId) {
        List<Book> books = bookRepository.findByAuthorId(authorId);

        if (books.isEmpty()) {
            throw new GlobalException("No books found for author with ID " + authorId);
        }

        return books.stream()
                .map(bookMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByAuthorName(String authorName) {
        List<Book> books = bookRepository.findByAuthorName(authorName);

        if (books.isEmpty()) {
            throw new GlobalException("No books found for author with name " + authorName);
        }

        return books.stream()
                .map(bookMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByGenre(String genre) {
        List<Book> books = bookRepository.findByGenre(genre);

        if (books.isEmpty()) {
            throw new GlobalException("No books found for genre " + genre);
        }

        return books.stream()
                .map(bookMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);

        if (books.isEmpty()) {
            throw new GlobalException("No books found for Title " + title);
        }

        return books.stream()
                .map(bookMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(bookMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksAvailable() {
        List<Book> books = bookRepository.findAllAvailableBooks();

        if (books.isEmpty()) {
            throw new GlobalException("No books available");
        }

        return books.stream()
                .map(bookMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void softDeleteBook(int bookId) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new GlobalException("Book with ID " + bookId + " not found"));

        existingBook.setUpdatedAt(new Date());
        existingBook.setDeletedAt(new Date());

        bookRepository.save(existingBook);
    }
}
