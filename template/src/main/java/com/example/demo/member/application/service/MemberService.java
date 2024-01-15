package com.example.demo.member.application.service;

import com.example.demo.member.application.port.in.MemberUseCase;
import com.example.demo.member.application.port.out.MemberJdbcPort;
import com.example.demo.member.application.port.out.MemberJpaPort;
import com.example.demo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final MemberJpaPort memberJpaPort;
    private final MemberJdbcPort memberJdbcPort;

    @Override
    @Transactional
    public Member registerMember(Member member) {

//        memberJdbcPort.save(member);
//        return null;

        return memberJpaPort.save(member);
    }
}
