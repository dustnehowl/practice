package com.example.practice2.member.controller;

import com.example.practice2.member.controller.dto.MemberRegistRequest;
import com.example.practice2.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody MemberRegistRequest memberRegistRequest){
        return ResponseEntity.ok().body(memberService.signUp(memberRegistRequest));
    }
    @GetMapping("/{memberId}")
    public ResponseEntity getMember(@PathVariable Long memberId){
        return ResponseEntity.ok().body(memberService.getMember(memberId));
    }
    @GetMapping("/search")
    public ResponseEntity searchMembers(@RequestParam String keyword){
        return ResponseEntity.ok().body(memberService.findByKeyword(keyword));
    }
    @GetMapping("/all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok().body(memberService.getAll());
    }
}
