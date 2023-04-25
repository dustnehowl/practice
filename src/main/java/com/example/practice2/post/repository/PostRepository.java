package com.example.practice2.post.repository;

import com.example.practice2.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findPostById(Long postId);
}
