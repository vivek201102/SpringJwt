package com.example.dbconnection.services;

import com.example.dbconnection.entity.UserEntity;

import java.util.List;

public interface UserServices {
    public UserEntity createUser(UserEntity user);
    public UserEntity getUserById(int id);
    public List<UserEntity> getAllUser();

}
