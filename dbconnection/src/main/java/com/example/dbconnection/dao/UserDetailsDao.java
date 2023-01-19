package com.example.dbconnection.dao;

import com.example.dbconnection.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsDao extends JpaRepository<UserDetails, Integer> {
    
}
