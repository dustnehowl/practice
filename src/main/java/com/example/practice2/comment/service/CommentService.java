package com.example.practice2.comment.service;

import com.example.practice2.comment.Comment;
import com.example.practice2.comment.controller.dto.CommentRequest;
import com.example.practice2.comment.controller.dto.CommentResponse;
import com.example.practice2.comment.repository.CommentRepository;
import com.example.practice2.member.Member;
import com.example.practice2.member.repository.MemberRepository;
import com.example.practice2.post.Post;
import com.example.practice2.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    @Transactional
    public CommentResponse comment(CommentRequest commentRequest){
        Member commenter = findMemberById(commentRequest.getCommenterId());
        Post post = findPostById(commentRequest.getPostId());
        String comment = commentRequest.getComment();
        Boolean isAnonymous = commentRequest.getIsAnonymous();

        Comment commentObject = Comment.builder()
                .commenter(commenter)
                .post(post)
                .comment(comment)
                .isAnonymous(isAnonymous)
                .build();
        commentRepository.save(commentObject);

        return CommentResponse.from(commentObject);
    }
    public CommentResponse getCommentById(Long commentId){
        return CommentResponse.from(findCommentById(commentId));
    }
    @Transactional
    public void deleteComment(Long commentId){
        commentRepository.delete(findCommentById(commentId));
    }
    public List<CommentResponse> getCommentsByPostId(Long postId){
        Post post = findPostById(postId);
        return CommentResponse.of(commentRepository.findCommentsByPost(post));
    }


    private Comment findCommentById(Long commentId){
        return commentRepository.findCommentById(commentId).orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));
    }
    private Member findMemberById(Long memberId){
        return memberRepository.findMemberById(memberId).orElseThrow(() -> new RuntimeException("멤버가 존재하지 않습니다."));
    }
    private Post findPostById(Long postId){
        return postRepository.findPostById(postId).orElseThrow(() -> new RuntimeException("포스트가 존재하지 않습니다."));
    }
}
