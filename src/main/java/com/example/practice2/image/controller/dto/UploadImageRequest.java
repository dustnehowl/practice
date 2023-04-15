package com.example.practice2.image.controller.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadImageRequest {
    private long memberId;
    private MultipartFile image;
}
