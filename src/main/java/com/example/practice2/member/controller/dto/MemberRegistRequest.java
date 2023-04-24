package com.example.practice2.member.controller.dto;

import lombok.Data;

@Data
public class MemberRegistRequest {
    private String loginId;
    private String nickName;
    private String password;
}
