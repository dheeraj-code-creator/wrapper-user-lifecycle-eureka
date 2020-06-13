
package com.springboot.rest.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import com.springboot.rest.client.ApiClient;
import com.springboot.rest.client.RestTemplateUserClient;

@Configuration
public class RestTemplateConfig {

	@Bean
	public ApiClient client() {
		return new RestTemplateUserClient();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	// for local environment
	@Bean
	@Profile("local")
	public RestTemplate restTemplateLocal(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		return restTemplate;
	}

	// for higher environments
	@Profile("!local")
	@Bean
	public RestTemplate restTemplateNotLocal(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		return restTemplate;
	}

}
