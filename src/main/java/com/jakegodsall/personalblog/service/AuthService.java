package com.jakegodsall.personalblog.service;

import com.jakegodsall.personalblog.payload.auth.LoginDto;
import com.jakegodsall.personalblog.payload.auth.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
