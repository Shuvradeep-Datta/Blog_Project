package com.deep.blogapis.services;

import java.util.List;

import org.springframework.stereotype.Component;


import com.deep.blogapis.payloads.UserDto;

@Component
public interface UserService {
	//Create
	UserDto createUser(UserDto userDto);
	//Update
	UserDto updateUser(UserDto userDto,Integer userId);
	//Read
	UserDto getUserById(Integer userId);
	//Read
	List<UserDto> getAllUser();
	//Delete
	void deleteUser(Integer userId);
	
	
	
	
	

}
