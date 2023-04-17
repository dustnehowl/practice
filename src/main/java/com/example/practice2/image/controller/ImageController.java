package com.example.practice2.image.controller;

import com.example.practice2.image.controller.dto.UploadImageRequest;
import com.example.practice2.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity uploadImage(@ModelAttribute UploadImageRequest uploadImageRequest){
        return ResponseEntity.ok().body(imageService.uploadImage(uploadImageRequest));
    }
    @GetMapping("/test")
    public String test(){
        return "Hello!";
    }

    @GetMapping("/getImages")
    public ResponseEntity getImagesByUploader(@RequestParam long uploaderId){
        return ResponseEntity.ok().body(imageService.getImagesByUploader(uploaderId));
    }
}
