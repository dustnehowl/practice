package com.example.practice2.post.controller.dto;

import com.example.practice2.image.PostImage;
import com.example.practice2.image.controller.dto.PostImageResponse;
import com.example.practice2.post.Post;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostResponse {
    private Long postId;
    private String uploader;
    private LocalDateTime uploadTime;
    private String imageUrl;

    @Builder
    public PostResponse(Long postId, String uploader, LocalDateTime uploadTime, String imageUrl){
        this.postId = postId;
        this.uploader = uploader;
        this.uploadTime =uploadTime;
        this.imageUrl = "https://slowybucket.s3.ap-northeast-2.amazonaws.com/" + imageUrl;
    }
    public static PostResponse from(Post post){
        return PostResponse.builder()
                .postId(post.getId())
                .uploader(post.getUploader().getNickName())
                .uploadTime(post.getUploadTime())
                .imageUrl(post.getPostImage().getStoreFileName())
                .build();
    }
    public static List<PostResponse> of(List<Post> posts){
        return posts.stream().map(PostResponse::from).collect(Collectors.toList());
    }
}
