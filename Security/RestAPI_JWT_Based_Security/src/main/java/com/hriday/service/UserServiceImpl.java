package com.hriday.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hriday.model.User;
import com.hriday.model.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	
	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}



	@Override
	public User saveUser(User user) {
		//Convert User password into unreadable format using BCryptPasswordEncoder and set again into User object .
				String pwd = user.getUserPwd();
				user.setUserPwd(pwdEncoder.encode(pwd));
		
		return userRepo.save(user);
	}



	@Override
	public User findUserBymail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findUserByuserMail(email);
	}


	//loadUserByUserName()method is used in Login Process.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepo.findUserByuserMail(email);
		List<String> roles = user.getRoles();
		
		//Create List to have GrantedAuthority Object. GrantedAuthority contains User's roles internally. 
		List<GrantedAuthority> grantedAuthority = new ArrayList<>();
		
		//Dumping roles into GrantedAuthority.
		for(String role : roles) {
			
			grantedAuthority.add(new SimpleGrantedAuthority(role));
		}
		//Create Spring Security User object.
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(email, user.getUserPwd(), grantedAuthority);
		
		return userDetails;
		
		
	}

}
