package com.example.practice2.member.service;

import com.example.practice2.member.Member;
import com.example.practice2.member.controller.dto.MemberRegistRequest;
import com.example.practice2.member.controller.dto.MemberResponse;
import com.example.practice2.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse regist(MemberRegistRequest memberRegistRequest) {
        findMemberByNameAndAge(memberRegistRequest.getName(), memberRegistRequest.getAge());
        Member member = new Member(memberRegistRequest.getName(), memberRegistRequest.getAge());
        memberRepository.save(member);
        return MemberResponse.from(member);
    }
    private void findMemberByNameAndAge(String name, int age){
        memberRepository.findMemberByNameAndAge(name, age)
                .ifPresent(member -> {
                    throw new RuntimeException("이미 존재하는 회원입니다.");
                });
    }
}
