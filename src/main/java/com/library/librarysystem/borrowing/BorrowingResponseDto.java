package com.library.librarysystem.borrowing;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BorrowingResponseDto {

    private Long id;
    private String memberName;
    private String bookTitle;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private Boolean isReturned;
}