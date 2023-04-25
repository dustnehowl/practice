package com.example.practice2.like;

import com.example.practice2.comment.Comment;
import com.example.practice2.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Liked {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKED_ID")
    private Long id;
    private LocalDateTime likeTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;

    public Liked(){}
    @Builder
    public Liked(Member member, Comment comment){
        this.likeTime = LocalDateTime.now();
        this.member = member;
        this.comment = comment;
    }
}
