package com.example.demo.user.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserRequest {

    private String id;
    private String password;

    public String validation() {
        // TODO policy와 연계 가능
        if(this.id.length() < 8 || this.id.length() > 15) {
            return "아이디는 8글자 이상 15글자 이하";
        }
        if(this.password.length() < 8 || this.password.length() > 15) {
            return "비밀번호는 8글자 이상 15글자 이하";
        }
        // TODO 그외 대소문자, 특수문자 조건 등
        return null;
    }
}