package com.example.practice2.image.controller.dto;

import com.example.practice2.image.Image;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ImageResponse {
    private long imageId;
    private String originalName;
    private String uploader;
    private LocalDateTime uploadTime;
    private String publicUrl;

    public ImageResponse(long id, String originalName, String uploader, LocalDateTime uploadTime, String storeFileName){
        this.imageId = id;
        this.originalName = originalName;
        this.uploader = uploader;
        this.uploadTime = uploadTime;
        this.publicUrl = "https://storage.googleapis.com/spring_practice/" + storeFileName;
    }
    public static ImageResponse from(Image image) {
        return new ImageResponse(
                image.getId(),
                image.getOriginalName(),
                image.getUploader().getName(),
                image.getUploadTime(),
                image.getStoreFileName()
        );
    }

    public static List<ImageResponse> of(List<Image> images){
        return images.stream().map(ImageResponse::from).collect(Collectors.toList());
    }
}
