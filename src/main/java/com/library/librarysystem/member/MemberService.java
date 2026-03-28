package com.library.librarysystem.member;

import com.library.librarysystem.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberResponseDto addMember(MemberRequestDto memberRequestDto) {
        Member saved = memberRepository.save(memberMapper.mapToEntity(memberRequestDto));
        return memberMapper.mapToResponseDto(saved);
    }

    public MemberResponseDto getMember(Long id) {
        return memberMapper.mapToResponseDto(
                memberRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "No member found with id: " + id)));
    }

    public MemberResponseDto getMember(String name) {
        return memberMapper.mapToResponseDto(
                memberRepository.findMemberByNameContainingIgnoreCase(name)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "No member found with name: " + name)));
    }

    public List<MemberResponseDto> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(memberMapper::mapToResponseDto)
                .toList();
    }

    public MemberResponseDto updateMember(String name, MemberRequestDto memberRequestDto) {
        var memberToUpdate = memberRepository
                .findMemberByNameContainingIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No member found with name: " + name));
        return memberMapper.mapToResponseDto(
                memberRepository.save(memberMapper.updateEntity(memberRequestDto, memberToUpdate)));
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}