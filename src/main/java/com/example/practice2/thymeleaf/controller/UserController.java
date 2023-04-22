package com.example.practice2.thymeleaf.controller;

import com.amazonaws.Response;
import com.example.practice2.thymeleaf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity getUser(@PathVariable Long userId){
        return ResponseEntity.ok().body(userService.getUser(userId));
    }
}
