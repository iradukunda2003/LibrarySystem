package com.library.librarysystem.borrowing;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class BorrowingRequestDto {

    @NotNull(message = "Member ID must not be null")
    private Long memberId;

    @NotNull(message = "Book ID must not be null")
    private Long bookId;
}