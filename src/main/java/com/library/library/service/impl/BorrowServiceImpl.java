package com.library.library.service.impl;

import com.library.library.dto.Book.BookDto;
import com.library.library.dto.Borrow.BorrowDto;
import com.library.library.entity.Book;
import com.library.library.entity.Borrow;
import com.library.library.entity.User;
import com.library.library.exeption.GlobalException;
import com.library.library.mapper.BookMapper;
import com.library.library.mapper.BorrowMapper;
import com.library.library.repository.BookRepository;
import com.library.library.repository.BorrowRepository;
import com.library.library.repository.UserRepository;
import com.library.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowMapper borrowMapper;
    private final BookMapper bookMapper;


    @Autowired
    public BorrowServiceImpl(BorrowRepository borrowRepository, UserRepository userRepository,
                             BookRepository bookRepository, BorrowMapper borrowMapper,
                             BookMapper bookMapper) {
        this.borrowRepository = borrowRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.borrowMapper = borrowMapper;
        this.bookMapper = bookMapper;
    }

    @Override
    public BorrowDto createBorrow(int bookId, int userId) {
        if (borrowRepository.isBookCurrentlyBorrowed(bookId)) {
            throw new GlobalException("This book is currently borrowed by another user.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException("User with ID " + userId + " not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new GlobalException("Book with ID " + bookId + " not found"));

        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setBook(book);
        borrow.setBorrowDate(new Date());
        borrow.setCreatedAt(new Date());

        Borrow savedBorrow = borrowRepository.save(borrow);

        return borrowMapper.entityToDto(savedBorrow);
    }

    @Override
    public BorrowDto getBorrowById(int borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new GlobalException("Borrowed information not found"));

        return borrowMapper.entityToDto(borrow);
    }

    @Override
    public List<BorrowDto> getAllBorrowsHistory() {
        List<Borrow> borrows = borrowRepository.findAll();

        return borrows.stream()
                .map(borrowMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksBorrowedByUserId(int userId) {
        List<Book> books = borrowRepository.findBooksBorrowedByUserId(userId);

        if (books.isEmpty()) {
            throw new GlobalException("No books found at user with ID " + userId);
        }

        return books.stream()
                .map(bookMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void returnBorrowedBook(int borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new GlobalException("Borrow record with ID " + borrowId + " not found"));

        borrow.setBroughtDate(new Date());
        borrow.setUpdatedAt(new Date());

        borrowRepository.save(borrow);
    }

}

