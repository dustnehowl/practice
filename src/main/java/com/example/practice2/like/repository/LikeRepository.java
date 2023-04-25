package com.example.practice2.like.repository;

import com.example.practice2.comment.Comment;
import com.example.practice2.like.Liked;
import com.example.practice2.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Liked, Long> {
    List<Liked> findLikedsByMember(Member member);
    List<Liked> findLikedsByComment(Comment comment);
}
