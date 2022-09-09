package com.programming.techie.springngblogv1.service;


import com.programming.techie.springngblogv1.dto.LoginRequest;
import com.programming.techie.springngblogv1.dto.RegisterRequest;
import com.programming.techie.springngblogv1.model.User;
import com.programming.techie.springngblogv1.repository.UserRepository;

import com.programming.techie.springngblogv1.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;



    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));

        userRepository.save(user);
    }


    public String login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtProvider.generateToken(authenticate);
    }



    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }


}