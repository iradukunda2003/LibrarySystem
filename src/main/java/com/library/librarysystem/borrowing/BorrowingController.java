package com.library.librarysystem.borrowing;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/borrowings")
@Tag(name = "Borrowing API", description = "All operations related to borrowing and returning books")
public class BorrowingController {

    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping
    @Operation(summary = "Borrow a book for a member")
    public ResponseEntity<BorrowingResponseDto> borrowBook(
            @Valid @RequestBody BorrowingRequestDto request) {
        return new ResponseEntity<>(borrowingService.borrowBook(request), HttpStatus.CREATED);
    }


    @PutMapping("/return/{bookId}")
    @Operation(summary = "Return a borrowed book by book ID")
    public ResponseEntity<BorrowingResponseDto> returnBook(@PathVariable Long bookId) {
        return new ResponseEntity<>(borrowingService.returnBook(bookId), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a borrowing record by its ID")
    public ResponseEntity<BorrowingResponseDto> getBorrowingById(@PathVariable Long id) {
        return new ResponseEntity<>(borrowingService.getBorrowingById(id), HttpStatus.OK);
    }


    @GetMapping("/member/{memberId}")
    @Operation(summary = "Get all borrowing records for a specific member")
    public ResponseEntity<List<BorrowingResponseDto>> getBorrowingsByMember(
            @PathVariable Long memberId) {
        return new ResponseEntity<>(borrowingService.getBorrowingsByMember(memberId), HttpStatus.OK);
    }
}
