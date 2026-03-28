package com.library.librarysystem.member;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByNameContainingIgnoreCase(String name);

    Optional<Member> findMemberByEmail(String email);
}