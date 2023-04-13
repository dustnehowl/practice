package com.example.practice2.image;

import com.example.practice2.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Image {
    @Id @Column(name = "IMAGE_NAME")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String originalName;
    private String storeFileName;
    private LocalDateTime uploadTime;
    @ManyToOne @JoinColumn(name = "UPLOADER")
    private Member uploader;

    @Builder
    public Image(String originalName, String storeFileName, Member uploader){
        this.originalName = originalName;
        this.storeFileName = storeFileName;
        this.uploadTime = LocalDateTime.now();
        this.uploader = uploader;
    }
    public Image(){}
}
