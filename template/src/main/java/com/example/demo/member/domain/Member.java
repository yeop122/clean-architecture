package com.example.demo.member.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private String userId;
    private String password;
    private String name;
}
