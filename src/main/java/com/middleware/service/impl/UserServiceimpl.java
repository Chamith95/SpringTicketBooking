package com.middleware.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.middleware.io.entity.UserEntity;
import com.middleware.repository.UserRepository;
import com.middleware.service.UserService;
import com.middleware.shared.Utils;
import com.middleware.shared.dto.UserDto;

@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		
	
		
		if(userRepository.findByEmail(user.getEmail()) !=null) throw new RuntimeException("RecordAlreadyExists");

		UserEntity userEntity =new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		
		String publicUserId =utils.generateUserId(30);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(publicUserId);
		
		UserEntity storedUserDetilas=userRepository.save(userEntity);
		
		UserDto returnValue=new UserDto();
		BeanUtils.copyProperties(storedUserDetilas,returnValue);
		
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity=userRepository.findByEmail(email); 

		
		if(userEntity ==null)throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity=userRepository.findByEmail(email); 
		
		if(userEntity ==null)return null;
		
		UserDto returnValue=new UserDto();
		BeanUtils.copyProperties(userEntity,returnValue);
		return returnValue;
	}

} 
