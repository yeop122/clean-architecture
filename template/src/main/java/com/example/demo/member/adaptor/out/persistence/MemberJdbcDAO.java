package com.example.demo.member.adaptor.out.persistence;

import com.example.demo.member.adaptor.out.entity.MemberEntity;
import com.example.demo.member.adaptor.out.entity.mapper.MemberMapper;
import com.example.demo.member.application.port.out.MemberJdbcPort;
import com.example.demo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:queries/member-queries.properties")
public class MemberJdbcDAO implements MemberJdbcPort {

    private final JdbcTemplate jdbcTemplate;
    private final MemberMapper memberMapper;

    @Value("member.save")
    private String saveQuery;

    @Override
    public int save(Member member) {

        MemberEntity memberEntity = memberMapper.toEntity(member);
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(memberEntity);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        return namedParameterJdbcTemplate.update(saveQuery, parameterSource);
    }
}
