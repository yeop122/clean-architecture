package com.example.demo.member.adaptor.out.persistence;

import com.example.demo.member.adaptor.out.entity.MemberEntity;

public interface MemberJdbcRepository {

    int save(MemberEntity memberEntity);
}
