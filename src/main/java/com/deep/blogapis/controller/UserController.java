package com.deep.blogapis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deep.blogapis.payloads.ApiResponse;
import com.deep.blogapis.payloads.UserDto;
import com.deep.blogapis.services.UserService;









@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private  UserService userService;
	
	
	//POST- CREATE USER
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser( @RequestBody UserDto userDto)
	{
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);	
	}
	
	//GET- READ USER
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getUser() {
		return ResponseEntity.ok(this.userService.getAllUser());

	}
	
	//GET - READ USER
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
		
	}
	
	
	// UPDATE-UPDATE USER

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(this.userService.updateUser(userDto, userId));

	}
	
	//DELETE-DELETE USER
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
		this.userService.deleteUser(userId);
		return  new ResponseEntity<ApiResponse> (new ApiResponse("Deleted Successfully",true),HttpStatus.OK);
	}
	
	
}
