package com.library.librarysystem.book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Book API", description = "All operations related to books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add a new book — ADMIN only")
    public ResponseEntity<Book> addBook(
            @Valid @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(
                bookService.addBook(bookDto), HttpStatus.CREATED);
    }


    @GetMapping
    @Operation(summary = "Get all books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(
                bookService.getAllBooks(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return new ResponseEntity<>(
                bookService.getBook(id), HttpStatus.OK);
    }


    @GetMapping("/title")
    @Operation(summary = "Get a book by title")
    public ResponseEntity<Book> getBookByTitle(
            @RequestParam String title) {
        return new ResponseEntity<>(
                bookService.getBookByTitle(title), HttpStatus.OK);
    }


    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a book — ADMIN only")
    public ResponseEntity<Book> updateBook(
            @RequestParam String title,
            @Valid @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(
                bookService.updateBook(title, bookDto), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a book — ADMIN only")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}