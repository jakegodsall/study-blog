package com.jakegodsall.personalblog.service;

import com.jakegodsall.personalblog.payload.LoginDto;
import com.jakegodsall.personalblog.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
