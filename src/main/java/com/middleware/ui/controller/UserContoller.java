package com.middleware.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.middleware.service.UserService;
import com.middleware.shared.dto.UserDto;
import com.middleware.ui.model.request.UserDetailsRequestModel;
import com.middleware.ui.model.response.UserRest;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("users")
public class UserContoller {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUser()
	{
		System.out.println("get was called");
		return "get user was called";
	}
	
	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails)
	{
		UserRest returnValue =new UserRest();
		System.out.println(userDetails.getFirstName());
		UserDto userDto =new UserDto();
		BeanUtils.copyProperties(userDetails,userDto);
		
		UserDto createdUser =userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		System.out.println(createdUser.getFirstName());
		
		return returnValue;
	}

	@PutMapping
	public String updateUSer()
	{
		return "Upadte user was called";
	}
	
	@DeleteMapping
	public String deletUser()
	{
		return "Delete user was called";
	}
}
