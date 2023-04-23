package com.example.practice2.thymeleaf.controller;

import com.amazonaws.Response;
import com.example.practice2.thymeleaf.domain.User;
import com.example.practice2.thymeleaf.dto.response.UserResponse;
import com.example.practice2.thymeleaf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ModelAndView showUser(@PathVariable Long userId, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-details");

        UserResponse userResponse = userService.getUser(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("userResponse", userResponse);
        modelAndView.addObject("data", map);

        model.addAttribute("userResponse", userResponse);
        return modelAndView;
    }
}
