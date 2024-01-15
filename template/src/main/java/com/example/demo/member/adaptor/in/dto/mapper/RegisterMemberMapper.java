package com.example.demo.member.adaptor.in.dto.mapper;

import com.example.demo.member.adaptor.in.dto.RegisterMemberRequest;
import com.example.demo.member.domain.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterMemberMapper {

    Member dtoToDomain(RegisterMemberRequest registerMemberRequest);
}
