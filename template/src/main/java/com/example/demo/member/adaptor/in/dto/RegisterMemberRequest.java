package com.example.demo.member.adaptor.in.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMemberRequest {

    private String userId;
    private String password;
    private String name;
}