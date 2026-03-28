package com.library.librarysystem.borrowing;

import com.library.librarysystem.book.Book;
import com.library.librarysystem.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "borrowing_record")
public class BorrowingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @CreationTimestamp
    @Column(name = "borrow_date")
    private LocalDateTime borrowDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "is_returned", nullable = false)
    private Boolean isReturned = false;
}
