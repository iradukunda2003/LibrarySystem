package com.library.librarysystem.book;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByIsbn(String isbn);


    Optional<Book> findBookByTitleContainingIgnoreCase(String title);

    List<Book> findBooksByAuthor(String author);
}