package com.example.practice2.post.service;

import com.example.practice2.image.PostImage;
import com.example.practice2.image.controller.dto.PostImageResponse;
import com.example.practice2.image.service.ImageService;
import com.example.practice2.member.Member;
import com.example.practice2.member.repository.MemberRepository;
import com.example.practice2.post.Post;
import com.example.practice2.post.controller.dto.PostRequest;
import com.example.practice2.post.controller.dto.PostResponse;
import com.example.practice2.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ImageService imageService;

    @Transactional
    public PostResponse upload(PostRequest postRequest){
        Member uploader = findMemberById(postRequest.getUploaderId());
        MultipartFile image = postRequest.getImage();

        PostImage postImage = imageService.uploadPostImageS3(postRequest);
        Post post = Post.builder()
                .uploader(uploader)
                .postImage(postImage)
                .build();
        postRepository.save(post);

        return PostResponse.from(post);
    }
    @Transactional
    public void deletePost(Long postId){
        Post post = findPostById(postId);
        postRepository.delete(post);
    }
    public PostResponse getPostById(Long postId){
        return PostResponse.from(findPostById(postId));
    }
    public List<PostResponse> getAll(){
        List<Post> posts = postRepository.findAll();
        return PostResponse.of(posts);
    }

    private Member findMemberById(Long memberId){
        return memberRepository.findMemberById(memberId).orElseThrow(() -> new RuntimeException());
    }
    private Post findPostById(Long postId){
        return postRepository.findPostById(postId).orElseThrow(() -> new RuntimeException());
    }

}
