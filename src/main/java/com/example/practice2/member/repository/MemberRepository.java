package com.example.practice2.member.repository;

import com.example.practice2.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberById(long memberId);
    Optional<Member> findMemberByNameAndAge(String name, int age);
    @Query("SELECT m FROM Member m WHERE m.name LIKE %:keyword%")
    List<Member> findByKeyword(@Param("keyword") String keyword);
}
