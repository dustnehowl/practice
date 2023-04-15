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
    private String name;
    private int age;

    @Builder
    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Member(){}
}
