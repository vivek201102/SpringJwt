package com.example.dbconnection.services.Impl;

import com.example.dbconnection.dao.UserDao;
import com.example.dbconnection.entity.UserEntity;
import com.example.dbconnection.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserDao userDao;
    @Override
    public UserEntity createUser(UserEntity user) {
        try{
            userDao.save(user);
        }
        catch (Exception e)
        {
            throw e;
        }
        return user;
    }

    @Override
    public UserEntity getUserById(int id) {
        UserEntity user = new UserEntity();
        try{
            user = userDao.getById(id);
        }
        catch (Exception e)
        {
            throw e;
        }

        return user;
    }

    @Override
    public List<UserEntity> getAllUser() {
        List<UserEntity> users;
        try {
            users =(List<UserEntity>)userDao.findAll();
        }
        catch (Exception e)
        {
            throw e;
        }
        return users;
    }
}
