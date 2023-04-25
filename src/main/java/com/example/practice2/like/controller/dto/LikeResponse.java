package com.example.practice2.like.controller.dto;

import com.example.practice2.like.Liked;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LikeResponse {
    private Long likeId;
    private String member;
    private String comment;
    private LocalDateTime likeTime;
    @Builder
    public LikeResponse(Long likeId, String member, String comment, LocalDateTime likeTime){
        this.likeId = likeId;
        this.member = member;
        this.comment = comment;
        this.likeTime = likeTime;
    }
    public static LikeResponse from(Liked liked){
        return LikeResponse.builder()
                .likeId(liked.getId())
                .member(liked.getMember().getNickName())
                .comment(liked.getComment().getComment())
                .likeTime(liked.getLikeTime())
                .build();
    }

    public static List<LikeResponse> of(List<Liked> likeds){
        return likeds.stream().map(LikeResponse::from).collect(Collectors.toList());
    }
    public LikeResponse() {}
}
