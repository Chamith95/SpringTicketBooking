package com.middleware.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public UserDto createUser(UserDto user) {
		
	
		
		if(userRepository.findByEmail(user.getEmail()) !=null) throw new RuntimeException("RecordAlreadyExists");

		UserEntity userEntity =new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		
		String publicUserId =utils.generateUserId(30);
		userEntity.setEncryptedPassword("test");
		userEntity.setUserId(publicUserId);
		
		UserEntity storedUserDetilas=userRepository.save(userEntity);
		
		UserDto returnValue=new UserDto();
		BeanUtils.copyProperties(storedUserDetilas,returnValue);
		
		return returnValue;
	}

} 
