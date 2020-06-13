package com.springboot.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.rest.client.ApiClient;
import com.springboot.rest.dto.UserDto;

@Service
public class UserDetailsService {
	
	@Autowired
	private ApiClient apiClient;
	
	public UserDto addUserForEntiy(UserDto userdto) {
		ResponseEntity<UserDto> responseEntity = apiClient.postRequestToEureka(userdto);
		return responseEntity.getBody();
	}

	@SuppressWarnings("unchecked")
	public List<UserDto> getAllUserInfo() {
		String getUri = null;
		List<UserDto> userDto = (List<UserDto>) apiClient.getRequestToEureka(getUri, Object.class);
		return userDto; 
	}

	public void updateUser(UserDto userdto, String userId) {
		ResponseEntity<UserDto> responseEntity = apiClient.putRequestToEureka(userdto, userId);
		responseEntity.getBody();
	}

	public UserDto getUserByUserId(String userId) {
		UserDto userDto = (UserDto) apiClient.getByIdRequestToEureka(userId, Object.class);
		return userDto;
	}

	public void deleteById(String userId) {
		apiClient.deleteRequestToEureka(userId);
	}
}