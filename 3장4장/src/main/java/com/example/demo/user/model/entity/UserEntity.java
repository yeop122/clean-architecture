package com.example.demo.user.model.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserEntity {

    private Long userId;
    private String id;
    private String password;
}
