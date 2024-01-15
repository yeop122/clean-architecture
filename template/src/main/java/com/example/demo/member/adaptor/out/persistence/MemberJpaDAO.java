package com.example.demo.member.adaptor.out.persistence;

import com.example.demo.member.adaptor.out.entity.MemberEntity;
import com.example.demo.member.adaptor.out.entity.mapper.MemberMapper;
import com.example.demo.member.application.port.out.MemberJdbcPort;
import com.example.demo.member.application.port.out.MemberJpaPort;
import com.example.demo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MemberJpaDAO implements MemberJpaPort {

    private final MemberMapper memberMapper;
    // JPA
    private final MemberJpaRepository memberJpaRepository;
    // JDBC Template
    private final MemberJdbcPort memberJdbcPort;

    /**
     * JPA 방식 save
     * @param member
     * @return saved entity
     */
    @Override
    public Member save(Member member) {
        MemberEntity memberEntity = memberMapper.toEntity(member);
        MemberEntity savedEntity = memberJpaRepository.save(memberEntity);
        return memberMapper.toDomain(savedEntity);
    }
}
