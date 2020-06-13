package com.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.dto.UserDto;
import com.springboot.rest.service.UserDetailsService;

@RestController
public class UserDetailsController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping(value = "/addNewUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> createNewUser(@RequestBody UserDto userdto) {
		userDetailsService.addUserForEntiy(userdto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/alluser", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDto> getUserinfo() {
		return userDetailsService.getAllUserInfo();
	}
	
	@PutMapping(value = "/updateUser/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> updateExistingUser(@RequestBody UserDto userdto, @PathVariable("userId")String userId) {
		userDetailsService.updateUser(userdto, userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto getUserById(@PathVariable("userId") String userId) {
		return userDetailsService.getUserByUserId(userId);
	}
	
	@DeleteMapping(value = "/delete/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUser(@PathVariable(value = "userId") String userId) {
		userDetailsService.deleteById(userId);
	}
}