package com.example.demo.member.application.port.out;

import com.example.demo.member.domain.Member;

public interface MemberJdbcPort {

    int save(Member member);
}
