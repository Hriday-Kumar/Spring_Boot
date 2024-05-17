package com.hriday.security.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class MySecurity extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("hriday").password("{noop}hriday").authorities("admin");
		auth.inMemoryAuthentication().withUser("visitor").password("{noop}1234").authorities("common");
		auth.inMemoryAuthentication().withUser("train").password("{noop}train").authorities("trainticket");
		auth.inMemoryAuthentication().withUser("hotel").password("{noop}hotel").authorities("hotelticket");
		auth.inMemoryAuthentication().withUser("fish").password("{noop}fish").authorities("fishticket");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/ticket").permitAll()
		.antMatchers("/go").permitAll()
		.antMatchers("/snake").authenticated()
		
		
		
		.antMatchers("/hotel").hasAuthority("hotelticket")
		.antMatchers("/fish").hasAuthority("fishticket")
		
		.and()
		.formLogin().permitAll()
		
		.and()
		.logout()
		
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied");
		
		
		
		
	}

	
	

}
