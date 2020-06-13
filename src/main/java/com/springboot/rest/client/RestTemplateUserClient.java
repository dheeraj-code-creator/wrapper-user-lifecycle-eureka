
package com.springboot.rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.springboot.rest.dto.UserDto;

public class RestTemplateUserClient implements ApiClient {

	@Value("${user.management.baseUrl}")
	private String baseUrl;

	@Autowired
	private RestTemplate restTemplate;
	
    @Autowired
	private LoadBalancerClient loadBalancer;

	public RestTemplateUserClient() {
		this.restTemplate = new RestTemplate();
	}
	
	@Override
	public ResponseEntity<UserDto> postRequestToEureka(UserDto userdto) {
		ServiceInstance serviceInstance = null;
		HttpEntity<?> httpEntity = new HttpEntity<>(userdto);
		serviceInstance = loadBalancer.choose("user-management");
		String newUrl = serviceInstance.getUri().toString();
		newUrl = newUrl + baseUrl + "/create";
		return restTemplate.exchange(newUrl, HttpMethod.POST,httpEntity,UserDto.class);
	}
	
	@Override
	public <T> T getRequestToEureka(String getUri, Class<T> tClasss) {
		ServiceInstance serviceInstance = null;
		serviceInstance = loadBalancer.choose("user-management");
		getUri = serviceInstance.getUri().toString();
		getUri = getUri + baseUrl + "/alluser";
		return restTemplate.getForObject(getUri, tClasss);
	}

	@Override
	public ResponseEntity<UserDto> putRequestToEureka(UserDto userdto, String userId) {
		ServiceInstance serviceInstance = null;
		HttpEntity<?> httpEntity = new HttpEntity<>(userdto);
		serviceInstance = loadBalancer.choose("user-management");
		String newUrl = serviceInstance.getUri().toString();
		newUrl = newUrl + baseUrl + "/update/" + userId;
		return restTemplate.exchange(newUrl, HttpMethod.PUT,httpEntity,UserDto.class);
	}

	@Override
	public UserDto getByIdRequestToEureka(String userId, Class<Object> objectClass) {
		ServiceInstance serviceInstance = null;
		serviceInstance = loadBalancer.choose("user-management");
		String newUrl = serviceInstance.getUri().toString();
		newUrl = newUrl + baseUrl +"/"+ userId;
		return restTemplate.getForObject(newUrl, UserDto.class);
	}

	@Override
	public void deleteRequestToEureka(String userId) {
		ServiceInstance serviceInstance = null;
		serviceInstance = loadBalancer.choose("user-management");
		String newUrl = serviceInstance.getUri().toString();
		newUrl = newUrl + baseUrl +"/delete/"+ userId;
		restTemplate.delete(newUrl);
	}
}
