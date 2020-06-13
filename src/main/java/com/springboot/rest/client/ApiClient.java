
package com.springboot.rest.client;

import org.springframework.http.ResponseEntity;

import com.springboot.rest.dto.UserDto;

public interface ApiClient {

	ResponseEntity<UserDto> postRequestToEureka(UserDto userdto);

	<T> T getRequestToEureka(String getUri, Class<T> tClass);

	ResponseEntity<UserDto> putRequestToEureka(UserDto userdto, String userId);

	UserDto getByIdRequestToEureka(String userId, Class<Object> objectClass);

	void deleteRequestToEureka(String userId);
}
