package com.example.practice2.thymeleaf.dto.response;

import com.example.practice2.thymeleaf.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResponse {
    private Long userId;
    private String nickname;

    @Builder
    public UserResponse(Long userId, String nickname){
        this.userId = userId;
        this.nickname = nickname;
    }

    public static UserResponse from(User user){
        return UserResponse.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .build();
    }
}
