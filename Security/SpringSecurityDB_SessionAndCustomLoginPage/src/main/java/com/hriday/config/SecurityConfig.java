package com.hriday.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	
	/*
	 * public SecurityConfig(BCryptPasswordEncoder pwdEncoder, UserDetailsService
	 * userDetailService) { super(); this.pwdEncoder = pwdEncoder;
	 * this.userDetailService = userDetailService; }
	 */

	@Override
	
	//Verify user name & password are valid or not.
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailService).passwordEncoder(pwdEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests()
		.antMatchers("/register","/save", "/home","/login").permitAll()
		.antMatchers("/admin").hasAuthority ("admin")
		.antMatchers("/mgr").hasAuthority ("manager")
		//.antMatchers("/common", "/denied").authenticated()
		.anyRequest() //Remaining all URLs. Other then which are not provided above
		.authenticated()
		
		.and()
		.formLogin()
		.loginPage("/login")//From Custom Login Page concept.
		.defaultSuccessUrl("/common", true)// Successfully logged in then go to common page.
		
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied");
		
		
	}
}

