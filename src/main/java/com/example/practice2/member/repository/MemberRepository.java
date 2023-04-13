package com.example.practice2.member.repository;

import com.example.practice2.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberById(long memberId);
    Optional<Member> findMemberByNameAndAge(String name, int age);
}
