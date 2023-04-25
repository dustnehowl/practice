package com.example.practice2.image.controller.dto;

import com.example.practice2.image.Image;
import com.example.practice2.image.PostImage;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostImageResponse {
    private Long postImageId;
    private String originalName;
    private String uploader;
    private LocalDateTime uploadTime;
    private String publicUrl;

    public PostImageResponse(long id, String originalName, String uploader, LocalDateTime uploadTime, String storeFileName){
        this.postImageId = id;
        this.originalName = originalName;
        this.uploader = uploader;
        this.uploadTime = uploadTime;
        this.publicUrl = "https://slowybucket.s3.ap-northeast-2.amazonaws.com/" + storeFileName;
    }
    public static PostImageResponse from(PostImage postImage) {
        return new PostImageResponse(
                postImage.getId(),
                postImage.getOriginalName(),
                postImage.getUploader().getNickName(),
                postImage.getUploadTime(),
                postImage.getStoreFileName()
        );
    }

    public static List<PostImageResponse> of(List<PostImage> postImages){
        return postImages.stream().map(PostImageResponse::from).collect(Collectors.toList());
    }
}
