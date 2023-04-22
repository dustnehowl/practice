package com.example.practice2.thymeleaf.service;

import com.example.practice2.thymeleaf.domain.User;
import com.example.practice2.thymeleaf.dto.response.UserResponse;
import com.example.practice2.thymeleaf.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse getUser(Long userId){
        return UserResponse.from(findUserById(userId));
    }


    private User findUserById(Long userId){
        return userRepository.findUserById(userId).orElseThrow(() -> new RuntimeException());
    }
}
