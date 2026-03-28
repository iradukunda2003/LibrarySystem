package com.library.librarysystem.book;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", nullable = false)

    private Long id;
    @NotBlank(message="Book title must not be blank")
    @Column(name ="book title", nullable = false )
    private String title;
    @NotBlank(message ="Author name must not be blank")
    @Column(name="book_author", nullable = false)
    private String author;

    @NotBlank(message = "ISBN  must not be blank")
    @Column(name = "book_isbn",unique = true,nullable = false)
    private String isbn;
    @Column(name="book_genre")
    private String genre;
    @NotNull(message = "Quantity must not be null")
    @Min(value = 0,message = "Quantity cannot be negative")
    @Column(name="book_quantity, nullable = false")
    private Integer Quantity;
}

