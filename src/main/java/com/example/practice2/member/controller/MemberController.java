package com.example.practice2.member.controller;

import com.example.practice2.member.controller.dto.MemberRegistRequest;
import com.example.practice2.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/regist")
    public ResponseEntity regist(@RequestBody MemberRegistRequest memberRegistRequest){
        return ResponseEntity.ok().body(memberService.regist(memberRegistRequest));
    }
    @GetMapping("/{memberId}")
    public ResponseEntity getMember(@PathVariable Long memberId){
        return ResponseEntity.ok().body(memberService.getMember(memberId));
    }

    @GetMapping("/search")
    public ResponseEntity searchMembers(@RequestParam String keyword){
        return ResponseEntity.ok().body(memberService.findByKeyword(keyword));
    }
}
