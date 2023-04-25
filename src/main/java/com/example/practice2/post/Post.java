package com.example.practice2.post;

import com.example.practice2.image.PostImage;
import com.example.practice2.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;

@Entity
@Getter
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    private LocalDateTime uploadTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member uploader;
    @OneToOne
    @JoinColumn(name = "IMAGE_ID")
    private PostImage postImage;

    @Builder
    public Post(Member uploader, PostImage postImage){
        this.uploader = uploader;
        this.postImage = postImage;
        this.uploadTime = LocalDateTime.now();
    }

    public Post(){}
}
