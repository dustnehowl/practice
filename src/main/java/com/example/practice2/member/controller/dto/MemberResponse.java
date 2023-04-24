package com.example.practice2.member.controller.dto;

import com.example.practice2.member.Member;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberResponse implements Serializable {
    private long memberId;
    private String loginId;
    private String nickName;

    public MemberResponse() {}
    @Builder
    public MemberResponse(long memberId, String loginId, String nickName){
        this.memberId = memberId;
        this.loginId = loginId;
        this.nickName = nickName;
    }

    public static MemberResponse from(Member member){
        return MemberResponse.builder()
                .memberId(member.getId())
                .loginId(member.getLoginId())
                .nickName(member.getNickName())
                .build();
    }
    public static List<MemberResponse> of(List<Member> members){
        return members.stream().map(member -> MemberResponse.from(member)).collect(Collectors.toList());
    }
}
