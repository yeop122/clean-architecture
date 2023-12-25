package com.example.demo.user.web;

import com.example.demo.common.model.BusinessException;
import com.example.demo.user.model.dto.UserRequest;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public Long addUser(UserRequest userRequest) {

        if(userRequest.validation() != null) {
            throw BusinessException.builder().build();
        }

        return userService.addUser(userRequest);
    }

}
