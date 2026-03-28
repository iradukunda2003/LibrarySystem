package com.library.librarysystem.auth;

import com.library.librarysystem.member.Member;
import com.library.librarysystem.member.MemberRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Auth API", description = "Register and Login")
public class MeController {

    private final MemberRepository memberRepository;

    public MeController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/me")
    @Operation(summary = "Get currently logged in member profile")
    public ResponseEntity<Member> getCurrentUser() {


        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();


        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new RuntimeException(
                        "Member not found"));

        return ResponseEntity.ok(member);
    }
}