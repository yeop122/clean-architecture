package com.example.demo.member.adaptor.out.persistence;

import com.example.demo.member.adaptor.out.entity.MemberEntity;
import com.example.demo.member.domain.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    MemberEntity toEntity(Member member);

    Member toDomain(MemberEntity memberEntity);
}
