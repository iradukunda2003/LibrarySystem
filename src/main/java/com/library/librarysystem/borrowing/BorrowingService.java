package com.library.librarysystem.borrowing;

import com.library.librarysystem.book.Book;
import com.library.librarysystem.book.BookRepository;
import com.library.librarysystem.exception.ResourceNotFoundException;
import com.library.librarysystem.member.Member;
import com.library.librarysystem.member.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BorrowingMapper borrowingMapper;

    public BorrowingService(BorrowingRepository borrowingRepository,
                            BookRepository bookRepository,
                            MemberRepository memberRepository,
                            BorrowingMapper borrowingMapper) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.borrowingMapper = borrowingMapper;
    }


    @Transactional
    public BorrowingResponseDto borrowBook(BorrowingRequestDto request) {
        Member member = (Member) memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No member found with id: " + request.getMemberId()));

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No book found with id: " + request.getBookId()));

        BorrowingRecord record = borrowingMapper.toEntity(member, book);
        return borrowingMapper.toResponseDto(borrowingRepository.save(record));
    }


    @Transactional
    public BorrowingResponseDto returnBook(Long bookId) {
        BorrowingRecord record = borrowingRepository
                .findByBookIdAndIsReturnedFalse(bookId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No active borrowing found for book id: " + bookId));

        record.setIsReturned(true);
        record.setReturnDate(LocalDateTime.now());
        return borrowingMapper.toResponseDto(borrowingRepository.save(record));
    }


    public List<BorrowingResponseDto> getBorrowingsByMember(Long memberId) {
        return borrowingRepository.findByMemberId(memberId)
                .stream()
                .map(borrowingMapper::toResponseDto)
                .toList();
    }


    public BorrowingResponseDto getBorrowingById(Long id) {
        return borrowingMapper.toResponseDto(
                borrowingRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "No borrowing record found with id: " + id)));
    }
}
