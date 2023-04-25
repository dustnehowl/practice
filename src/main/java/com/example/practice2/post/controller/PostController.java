package com.example.practice2.post.controller;

import com.example.practice2.post.controller.dto.PostRequest;
import com.example.practice2.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    @PostMapping("/upload")
    public ResponseEntity upload(@ModelAttribute PostRequest postRequest){
        return ResponseEntity.ok().body(postService.upload(postRequest));
    }
    @GetMapping("/all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok().body(postService.getAll());
    }
    @GetMapping("/{postId}")
    public ResponseEntity getPostById(@PathVariable Long postId){
        return ResponseEntity.ok().body(postService.getPostById(postId));
    }
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }

}
