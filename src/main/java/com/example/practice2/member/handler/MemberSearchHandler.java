package com.example.practice2.member.handler;

import com.example.practice2.member.controller.dto.MemberResponse;
import com.example.practice2.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

@RequiredArgsConstructor
public class MemberSearchHandler extends TextWebSocketHandler {
    private final MemberService memberService;
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String keyword = message.getPayload();
        List<MemberResponse> results = memberService.findByKeyword(keyword);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(results);
        session.sendMessage(new TextMessage(json));
    }
}
