package com.hriday.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	//In case of invalid user then show error message.
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	
	/*
	 * public SecurityConfig(BCryptPasswordEncoder pwdEncoder, UserDetailsService
	 * userDetailService) { super(); this.pwdEncoder = pwdEncoder;
	 * this.userDetailService = userDetailService; }
	 */
	
	
	//We should add extra code for NON_FORM login concept else not required.
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}

	@Override
	
	//Verify user name & password are valid or not.  
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailService).passwordEncoder(pwdEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.csrf()
		.disable()// It's default enabled for Form based login. 
		.authorizeRequests()
		.antMatchers("/user/save", "/user/login").permitAll()
			
		.and()
		
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint)
		
		.and()
		//We will telling that it's stateless means rest api so don't create or manager SESSION things.
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
				
		
	}
}

