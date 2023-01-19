package com.example.dbconnection.controller;

import com.example.dbconnection.dao.UserDao;
import com.example.dbconnection.dao.UserDetailsDao;
import com.example.dbconnection.dto.AuthResponseDto;
import com.example.dbconnection.dto.LoginDto;
import com.example.dbconnection.dto.RegisterDto;
import com.example.dbconnection.entity.UserDetails;
import com.example.dbconnection.entity.UserEntity;
import com.example.dbconnection.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthContorller {
    private UserDao userDao;
    private UserDetailsDao userDetailsDao;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    private TokenGenerator tokenGenerator;

    @Autowired
    public AuthContorller(UserDao userDao, UserDetailsDao userDetailsDao,
                          AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder, TokenGenerator tokenGenerator) {
        this.userDao = userDao;
        this.userDetailsDao = userDetailsDao;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }



    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto)
    {
        if(userDao.existsByUsername(registerDto.getUsername()))
        {
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = registerDto.getUser();
        UserDetails userDetails = registerDto.getUserDetail();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        userDetails.setUserEntity(user);
        userDetailsDao.save(userDetails);
        return new ResponseEntity<>("User regested successfully", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto)
    {
        if(userDao.existsByEmail(loginDto.getUsername()))
        {
            loginDto.setUsername(userDao.findByEmail(loginDto.getUsername()).getUsername());
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenGenerator.generateToken(authentication);


        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

}
