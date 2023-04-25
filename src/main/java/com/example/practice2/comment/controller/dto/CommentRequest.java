package com.example.practice2.comment.controller.dto;

import lombok.Data;

@Data
public class CommentRequest {
    private Long postId;
    private Long commenterId;
    private String comment;
    private Boolean isAnonymous;
}
