package com.teksoi.restapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class BeanConfiguration {

	@Bean
	@RequestScope
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
