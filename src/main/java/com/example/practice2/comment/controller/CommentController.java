package com.example.practice2.comment.controller;

import com.example.practice2.comment.controller.dto.CommentRequest;
import com.example.practice2.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("")
    public ResponseEntity comment(@RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok().body(commentService.comment(commentRequest));
    }
    @GetMapping("/{commentId}")
    public ResponseEntity getCommentById(@PathVariable Long commentId){
        return ResponseEntity.ok().body(commentService.getCommentById(commentId));
    }
    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "OK!";
    }

    @GetMapping("/getComments/{postId}")
    public ResponseEntity getComments(@PathVariable Long postId){
        return ResponseEntity.ok().body(commentService.getCommentsByPostId(postId));
    }

}
