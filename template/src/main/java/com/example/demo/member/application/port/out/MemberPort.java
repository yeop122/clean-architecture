package com.example.demo.member.application.port.out;

import com.example.demo.member.domain.Member;

public interface MemberPort {

    Member save(Member member);
}
