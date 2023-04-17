package com.example.practice2.image.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.practice2.image.Image;
import com.example.practice2.image.controller.dto.ImageResponse;
import com.example.practice2.image.controller.dto.UploadImageRequest;
import com.example.practice2.image.repository.ImageRepository;
import com.example.practice2.member.Member;
import com.example.practice2.member.repository.MemberRepository;
import com.fasterxml.jackson.core.json.UTF8StreamJsonParser;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.common.base.Charsets;
import com.google.common.base.Utf8;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.google.common.base.Charsets.UTF_8;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final MemberRepository memberRepository;
    private final AmazonS3Client amazonS3Client;
    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    private Member findMemberById(long memberId) {
        Member member = memberRepository.findMemberById(memberId).orElseThrow(() -> new RuntimeException());
        return member;
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalName) {
        int pos = originalName.lastIndexOf(".");
        return originalName.substring(pos + 1);
    }

    public List<ImageResponse> getImagesByUploader(long uploaderId) {
        Member uploader = findMemberById(uploaderId);
        List<Image> images = imageRepository.findImagesByUploader(uploader);
        return ImageResponse.of(images);
    }

    @Transactional
    public ImageResponse uploadImageS3(UploadImageRequest uploadImageRequest) {
        Member uploader = findMemberById(uploadImageRequest.getMemberId());
        MultipartFile file = uploadImageRequest.getImage();
        Image image = uploadFileToS3(file, uploader);
        imageRepository.save(image);
        return ImageResponse.from(image);
    }

    private Image uploadFileToS3(MultipartFile file, Member uploader){
        try{
            String originalName = file.getOriginalFilename();
            String storeFileName = createStoreFileName(originalName);

            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucket,storeFileName,file.getInputStream(),metadata);

            return Image.builder()
                    .originalName(originalName)
                    .storeFileName(storeFileName)
                    .uploader(uploader)
                    .build();
        }
        catch (Exception e){
            throw new RuntimeException("이미지 업로드에 실패했습니다.");
        }
    }
}
