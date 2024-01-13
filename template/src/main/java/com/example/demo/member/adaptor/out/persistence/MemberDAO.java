package com.example.demo.member.adaptor.out.persistence;

import com.example.demo.common.util.modelmapper.ModelMapperWrapper;
import com.example.demo.member.adaptor.out.entity.MemberEntity;
import com.example.demo.member.application.port.out.MemberPort;
import com.example.demo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MemberDAO implements MemberPort {

//    private final MemberMapper memberMapper;
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
//        MemberEntity memberEntity = memberMapper.toEntity(member);
        MemberEntity memberEntity = ModelMapperWrapper.map(member, MemberEntity.class);
        MemberEntity savedEntity = memberJpaRepository.save(memberEntity);
//        return memberMapper.toDomain(savedEntity);
        return ModelMapperWrapper.map(savedEntity, Member.class);
    }

    /**
     * jdbcTemplate 방식 save
     * @param member
     * @return integer
     */
    public int save2(Member member) {
//        MemberEntity memberEntity = memberMapper.toEntity(member);
        ModelMapper modelMapper = new ModelMapper();
        MemberEntity memberEntity = modelMapper.map(member, MemberEntity.class);
        return memberJdbcRepository.save(memberEntity);
    }
}
