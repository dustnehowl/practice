package com.example.practice2.comment;

import com.example.practice2.member.Member;
import com.example.practice2.post.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;
    private LocalDateTime commentTime;
    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member commenter;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;
    private String comment;
    private Boolean isAnonymous;
    private Integer numUp;
    @Builder
    public Comment(Member commenter, Post post, String comment, Boolean isAnonymous){
        this.commentTime = LocalDateTime.now();
        this.commenter = commenter;
        this.post = post;
        this.comment = comment;
        this.isAnonymous = isAnonymous;
        this.numUp = 0;
    }
    public Comment(){}
}
