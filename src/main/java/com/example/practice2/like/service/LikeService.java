package com.example.practice2.like.service;

import com.example.practice2.comment.Comment;
import com.example.practice2.comment.repository.CommentRepository;
import com.example.practice2.like.Liked;
import com.example.practice2.like.controller.dto.LikeRequest;
import com.example.practice2.like.controller.dto.LikeResponse;
import com.example.practice2.like.repository.LikeRepository;
import com.example.practice2.member.Member;
import com.example.practice2.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    @Transactional
    public LikeResponse like(LikeRequest likeRequest){
        Member member = findMemberById(likeRequest.getMemberId());
        Comment comment = findCommentById(likeRequest.getCommentId());
        Liked liked = Liked.builder()
                .member(member)
                .comment(comment)
                .build();
        likeRepository.save(liked);
        return LikeResponse.from(liked);
    }

    private Member findMemberById(Long memberId){
        return memberRepository.findMemberById(memberId).orElseThrow(() -> new RuntimeException("멤버가 존재하지 않습니다."));
    }
    private Comment findCommentById(Long commentId){
        return commentRepository.findCommentById(commentId).orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));
    }
}
