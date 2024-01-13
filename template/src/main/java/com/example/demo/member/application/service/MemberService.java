package com.example.demo.member.application.service;

import com.example.demo.member.application.port.in.MemberUseCase;
import com.example.demo.member.application.port.out.MemberPort;
import com.example.demo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final MemberPort memberPort;

    @Override
    @Transactional
    public Member registerMember(Member member) {

        return memberPort.save(member);
    }
}
