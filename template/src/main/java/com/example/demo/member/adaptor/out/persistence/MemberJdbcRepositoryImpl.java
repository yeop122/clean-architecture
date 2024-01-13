package com.example.demo.member.adaptor.out.persistence;

import com.example.demo.member.adaptor.out.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:queries/member-queries.properties")
public class MemberJdbcRepositoryImpl implements MemberJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Value("member.save")
    private String saveQuery;

    @Override
    public int save(MemberEntity member) {

        return jdbcTemplate.update(saveQuery);
    }
}
