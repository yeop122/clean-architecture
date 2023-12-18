package com.example.demo.user.service;

import com.example.demo.user.model.dto.UserRequest;

public interface UserService {

    Long addUser(UserRequest userRequest);
}
