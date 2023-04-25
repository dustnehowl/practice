package com.example.practice2.like.controller.dto;

import lombok.Data;

@Data
public class LikeRequest {
    private Long memberId;
    private Long commentId;
}
