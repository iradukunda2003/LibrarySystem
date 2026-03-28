package com.library.librarysystem.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
public class BookDto {

    @Length(message = "Title must be at least 2 characters", min = 2, max = 100)
    @NotBlank(message = "Book title must not be blank")
    private String title;

    @NotBlank(message = "Author name must not be blank")
    private String author;

    @NotBlank(message = "ISBN must not be blank")
    private String isbn;


    private String genre;

    @NotNull(message = "Quantity must not be null")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;
}
