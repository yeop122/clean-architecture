package com.example.demo.member.adaptor.out.persistence;

import com.example.demo.member.adaptor.out.entity.MemberEntity;
import com.example.demo.member.application.port.out.MemberPort;
import com.example.demo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MemberDAO implements MemberPort {

    private final MemberMapper memberMapper;
    // JPA
    private final MemberJpaRepository memberJpaRepository;
    // JDBC Template
    private final MemberJdbcRepository memberJdbcRepository;

    /**
     * JPA 방식 save
     * @param member
     * @return saved entity
     */
    public Member save(Member member) {
        MemberEntity memberEntity = memberMapper.toEntity(member);
        MemberEntity savedEntity = memberJpaRepository.save(memberEntity);
        return memberMapper.toDomain(savedEntity);
    }

    /**
     * jdbcTemplate 방식 save
     * @param member
     * @return integer
     */
    public int save2(Member member) {
        MemberEntity memberEntity = memberMapper.toEntity(member);
        return memberJdbcRepository.save(memberEntity);
    }
}
