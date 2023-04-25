package com.example.practice2.image;

import com.example.practice2.member.Member;
import com.example.practice2.post.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class PostImage {
    @Id
    @Column(name = "POSTIMAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String originalName;
    private String storeFileName;
    private LocalDateTime uploadTime;
    @ManyToOne @JoinColumn(name = "UPLOADER")
    private Member uploader;

    @Builder
    public PostImage(String originalName, String storeFileName, Member uploader){
        this.originalName = originalName;
        this.storeFileName = storeFileName;
        this.uploadTime = LocalDateTime.now();
        this.uploader = uploader;
    }
    public PostImage(){}
}
