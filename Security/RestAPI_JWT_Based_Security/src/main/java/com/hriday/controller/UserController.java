package com.hriday.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hriday.model.JWT_RequestEntity;
import com.hriday.model.JWT_ResponseEntity;
import com.hriday.model.User;
import com.hriday.service.UserService;
import com.hriday.util.JWT_Encoder_Decoder;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	//It will verify user and password on request.
	private AuthenticationManager authManager;
	
	@Autowired
	private JWT_Encoder_Decoder jwt;
	
	
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		
		User user1 = userService.saveUser(user);
		
		String message = "User : "+user1.getUserName()+" is saved with id="+user1.getId()+" successfully !";
		
		return ResponseEntity.ok(message);
		
		
	}
	@PostMapping("/login")
	public ResponseEntity<JWT_ResponseEntity> validateLogin(@RequestBody JWT_RequestEntity req) { 
		
		//It will validate with DB record.
		authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmailId(), req.getPassword()));
		
		String token = jwt.getJsonWebToken_JWT(req.getEmailId());
		
		return ResponseEntity.ok(new JWT_ResponseEntity("SUCCESS", token));
	}
	
	@GetMapping("/get_user")
	public ResponseEntity<User> getUserDetail(@RequestParam String emailId, @RequestHeader String token){
		User user=null;
		if(jwt.validateToken_SubjectAgainstUser(token, emailId)) {
			
			user = userService.findUserBymail(emailId);
			return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
		}
	}
	
	
}
