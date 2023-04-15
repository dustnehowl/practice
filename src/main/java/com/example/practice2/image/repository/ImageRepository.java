package com.example.practice2.image.repository;

import com.example.practice2.image.Image;
import com.example.practice2.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findImageById(long imageId);
    List<Image> findImagesByUploader(Member member);
}
