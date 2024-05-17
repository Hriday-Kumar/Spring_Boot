package com.hriday.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
	
	@GetMapping("/security")
	public String getMessage() {
		
		return "You passed security checks";
	}

}
