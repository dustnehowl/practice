package com.example.practice2.like.controller;

import com.example.practice2.like.controller.dto.LikeRequest;
import com.example.practice2.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;
    @PostMapping("")
    public ResponseEntity like(@RequestBody LikeRequest likeRequest){
        return ResponseEntity.ok().body(likeService.like(likeRequest));
    }
}
