package com.example.practice2.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private long id;
    private String loginId;
    private String nickName;
    private String password;

    @Builder
    public Member(String loginId, String nickName, String password) {
        this.loginId = loginId;
        this.nickName = nickName;
        this.password = password;
    }

    public Member(){}
}
