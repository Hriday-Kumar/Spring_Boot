package com.hriday.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {
	@Bean // Creating object in container
	public BCryptPasswordEncoder pwdEncoder() {
	
	return new BCryptPasswordEncoder();
}}
