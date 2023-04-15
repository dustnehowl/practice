package com.example.practice2.image.service;

import com.example.practice2.image.Image;
import com.example.practice2.image.controller.dto.ImageResponse;
import com.example.practice2.image.controller.dto.UploadImageRequest;
import com.example.practice2.image.repository.ImageRepository;
import com.example.practice2.member.Member;
import com.example.practice2.member.repository.MemberRepository;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final MemberRepository memberRepository;
    private final Storage storage;
    @Transactional
    public ImageResponse uploadImage(UploadImageRequest uploadImageRequest) {
        Member uploader = findMemberById(uploadImageRequest.getMemberId());
        MultipartFile file = uploadImageRequest.getImage();
        Image image = uploadFileToGcs(file, uploader);
        imageRepository.save(image);
        return ImageResponse.from(image);
    }
    private Image uploadFileToGcs(MultipartFile file, Member uploader){
        try{
            String originalName = file.getOriginalFilename();
            String storeFileName = createStoreFileName(originalName);

            // 이미지 업로드
            byte[] content = file.getBytes();
            String bucketName = "spring_practice";
            BlobId blobId = BlobId.of(bucketName, storeFileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(file.getContentType())
                    .build();
            storage.create(blobInfo, content);

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
}
