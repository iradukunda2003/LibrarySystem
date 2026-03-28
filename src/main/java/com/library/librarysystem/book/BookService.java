package com.library.librarysystem.book;

import com.library.librarysystem.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public Book addBook(BookDto bookDto) {
        return bookRepository.save(bookMapper.mapToEntity(bookDto));
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No book found with id: " + id));
    }


    public Book getBookByTitle(String title) {
        return bookRepository.findBookByTitleContainingIgnoreCase(title)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No book found with title: " + title));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(String title, BookDto bookDto) {
        var bookToUpdate = bookRepository
                .findBookByTitleContainingIgnoreCase(title)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No book found with title: " + title));
        return bookRepository.save(bookMapper.updateEntity(bookDto, bookToUpdate));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}