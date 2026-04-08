package com.library.librarysystem.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
@Tag(name = "Member API", description = "All operations related to members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping
    @Operation(summary = "Add a new member")
    public ResponseEntity<MemberResponseDto> addMember(
            @Valid @RequestBody MemberRequestDto memberRequestDto) {
        return new ResponseEntity<>(
                memberService.addMember(memberRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all members — ADMIN only")
    public ResponseEntity<List<MemberResponseDto>> getAllMembers() {
        return new ResponseEntity<>(
                memberService.getAllMembers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a member by ID")
    public ResponseEntity<MemberResponseDto> getMemberById(
            @PathVariable Long id) {
        return new ResponseEntity<>(
                memberService.getMember(id), HttpStatus.OK);
    }


    @GetMapping("/name")
    @Operation(summary = "Get a member by name")
    public ResponseEntity<MemberResponseDto> getMemberByName(
            @RequestParam String name) {
        return new ResponseEntity<>(
                memberService.getMember(name), HttpStatus.OK);
    }


    @PutMapping
    @Operation(summary = "Update a member by name")
    public ResponseEntity<MemberResponseDto> updateMember(
            @RequestParam String name,
            @Valid @RequestBody MemberRequestDto memberRequestDto) {
        return new ResponseEntity<>(
                memberService.updateMember(name, memberRequestDto), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a member — ADMIN only")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
