package com.ap.SPRlibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.ap.SPRlibrary.services.BookService;
import com.ap.SPRlibrary.services.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ConfigForTest {
	@Bean
	@Primary
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

}
