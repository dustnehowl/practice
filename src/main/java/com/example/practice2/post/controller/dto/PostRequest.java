package com.example.practice2.post.controller.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostRequest {
    private Long uploaderId;
    private MultipartFile image;
}
