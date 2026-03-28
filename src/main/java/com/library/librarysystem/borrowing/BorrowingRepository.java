package com.library.librarysystem.borrowing;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BorrowingRepository extends JpaRepository<BorrowingRecord, Long> {


    List<BorrowingRecord> findByMemberId(Long memberId);


    Optional<BorrowingRecord> findByBookIdAndIsReturnedFalse(Long bookId);
}
