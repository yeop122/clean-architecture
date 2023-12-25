package com.example.demo.user.service;

import com.example.demo.common.util.modelmapper.ModelMapperWrapper;
import com.example.demo.user.model.dto.UserRequest;
import com.example.demo.user.model.entity.UserEntity;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long addUser(UserRequest userRequest) {

        UserEntity userEntity = ModelMapperWrapper.map(userRequest, UserEntity.class);
        userRepository.addUser(userEntity);
        return userEntity.getUserId();
    }
}
