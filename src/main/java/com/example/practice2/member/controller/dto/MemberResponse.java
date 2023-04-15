package com.example.practice2.member.controller.dto;

import com.example.practice2.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberResponse {
    private long memberId;
    private String name;
    private int age;

    @Builder
    public MemberResponse(long memberId, String name, int age){
        this.memberId = memberId;
        this.name = name;
        this.age = age;
    }

    public static MemberResponse from(Member member){
        return MemberResponse.builder()
                .memberId(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .build();
    }
}
