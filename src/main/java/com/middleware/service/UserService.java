package com.middleware.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.middleware.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
}
