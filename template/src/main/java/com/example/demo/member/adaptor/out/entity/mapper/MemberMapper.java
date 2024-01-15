package com.example.demo.member.adaptor.out.entity.mapper;

import com.example.demo.member.adaptor.out.entity.MemberEntity;
import com.example.demo.member.domain.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    @Mapping(target = "memberId", ignore = true)
    MemberEntity toEntity(Member member);

    Member toDomain(MemberEntity memberEntity);
}
