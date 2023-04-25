package com.example.practice2.comment.repository;

import com.example.practice2.comment.Comment;
import com.example.practice2.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findCommentById(Long commentId);
    List<Comment> findCommentsByPost(Post post);
}
