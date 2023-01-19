package com.example.dbconnection.dao;

import com.example.dbconnection.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity, Integer> {
    public UserEntity getById(int id);
    public UserEntity findByEmail(String email);
    public Optional<UserEntity> findByUsername(String username);
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
}
