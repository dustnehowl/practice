package com.example.practice2.comment.controller.dto;

import com.example.practice2.comment.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CommentResponse {
    private Long commentId;
    private Long postId;
    private String commenter;
    private LocalDateTime commentTime;
    private String comment;
    private Integer numLike;

    public  CommentResponse(Long commentId, Boolean isAnonymous, String commenter, LocalDateTime commentTime, String comment, Long postId, Integer numLike){
        this.commentId = commentId;
        if(isAnonymous == true) this.commenter = "Anonymous";
        else this.commenter = commenter;
        this.commentTime = commentTime;
        this.comment = comment;
        this.postId = postId;
        this.numLike = numLike;
    }
    public static CommentResponse from(Comment comment){
        return new CommentResponse(
                comment.getId(),
                comment.getIsAnonymous(),
                comment.getCommenter().getNickName(),
                comment.getCommentTime(),
                comment.getComment(),
                comment.getPost().getId(),
                comment.getNumLike()
        );
    }
    public static List<CommentResponse> of(List<Comment> comments){
        return comments.stream().map(CommentResponse::from).collect(Collectors.toList());
    }
}
