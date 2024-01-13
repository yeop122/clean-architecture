package com.example.demo.member.adaptor.in.web;

import com.example.demo.common.util.modelmapper.ModelMapperWrapper;
import com.example.demo.member.adaptor.in.dto.RegisterMemberRequest;
import com.example.demo.member.application.port.in.MemberUseCase;
import com.example.demo.member.domain.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
//@Validated
public class MemberController {

    private final MemberUseCase memberUseCase;
//    private final RegisterMemberMapper registerMemberMapper;

    @PostMapping
    public Member addUser(@RequestBody RegisterMemberRequest registerMemberRequest) {

//        Member member = registerMemberMapper.dtoToDomain(registerMemberRequest);
        Member member = ModelMapperWrapper.map(registerMemberRequest, Member.class);
        return memberUseCase.registerMember(member);
    }

}
