package com.example.practice2.config;

import com.example.practice2.member.handler.MemberSearchHandler;
import com.example.practice2.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final MemberService memberService;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(searchHandler(), "/search").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler searchHandler() {
        return new MemberSearchHandler(memberService);
    }
}
