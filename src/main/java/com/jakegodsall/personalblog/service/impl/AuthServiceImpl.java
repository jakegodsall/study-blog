package com.jakegodsall.personalblog.service.impl;

import com.jakegodsall.personalblog.entity.auth.Role;
import com.jakegodsall.personalblog.entity.auth.User;
import com.jakegodsall.personalblog.exception.BlogAPIException;
import com.jakegodsall.personalblog.payload.auth.LoginDto;
import com.jakegodsall.personalblog.payload.auth.RegisterDto;
import com.jakegodsall.personalblog.repository.auth.RoleRepository;
import com.jakegodsall.personalblog.repository.auth.UserRepository;
import com.jakegodsall.personalblog.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication
                = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                    ));
        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        authentication
                );

        return "User logged-in successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {
        // Check whether the username already exists in the database
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new BlogAPIException("Username already exists.");
        }
        // Check whether the email already exists in the database
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BlogAPIException("Email already exists.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository
                .findByName("ROLE_USER")
                .get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully";
    }
}
