package com.library.library.repository;

import com.library.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByAuthorId(int authorId);

    @Query("SELECT b FROM Book b WHERE b.author.name = :authorName")
    List<Book> findByAuthorName(String authorName);

    @Query("SELECT b FROM Book b WHERE b.genre = :genre")
    List<Book> findByGenre(String genre);

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    List<Book> findByTitle(String title);

    // Query to find all available books (not borrowed, returned or available from the start)
    @Query("SELECT DISTINCT b FROM Book b LEFT JOIN Borrow br ON b.id = br.book.id " +
            "WHERE (br.book IS NULL OR br.broughtDate IS NOT NULL) AND b.deletedAt IS NULL")
    List<Book> findAllAvailableBooks();


//    @Query("SELECT DISTINCT b FROM Book b LEFT JOIN Borrow br ON b.id = br.book.id " +
//            "WHERE br.book IS NULL OR br.broughtDate IS NOT NULL")
//    List<Book> findAllAvailableBooks();
}
