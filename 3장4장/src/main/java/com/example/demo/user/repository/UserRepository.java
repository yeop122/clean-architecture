package com.example.demo.user.repository;

import com.example.demo.user.model.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    public Integer addUser(UserEntity userEntity);
}
