package com.example.demo.member.application.port.in;

import com.example.demo.member.domain.Member;

public interface MemberUseCase {

    Member registerMember(Member member);
}
