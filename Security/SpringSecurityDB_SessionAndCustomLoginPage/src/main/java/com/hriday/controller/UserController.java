package com.hriday.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hriday.model.User;
import com.hriday.service.UserService;

@Controller
@SessionAttributes({"date"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String showReg() {
		
		return "UserRegister";
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute User user, Model model) {
		
		User user1 = userService.saveUser(user);
		
		String message = "User : "+user1.getUserName()+" is saved with id="+user1.getId()+" successfully !";
		//String message=   "User "+user1.getId()+" saved";
		model.addAttribute("message",message);
		return "UserRegister";
		
		
	}
	@GetMapping("/login")
	public String doLogin() { 
		return "CustomLoginPage";
	}
	
	@GetMapping("/home")
	public String showLogin() { 
		return "HomePage";
	}
	@GetMapping("/admin")
	public String showAdmin(Principal principle, Model model) { 
		String name = principle.getName();
		model.addAttribute("name", name);
		model.addAttribute("date", new Date());
		return "AdminPage";
	}
	@GetMapping("/mgr")
	public String showManager(Principal principle, Model model) { 
		String name = principle.getName();
		model.addAttribute("name", name);
		model.addAttribute("date", new Date());
		return "ManagerPage";
	}
	@GetMapping("/common")
	public String showCommon(Principal principle, Model model) { 
		String name = principle.getName();
		model.addAttribute("name", name);
		model.addAttribute("date", new Date());
		return "CommonPage";}
	
	@GetMapping("/denied")
	public String showNoAccess() { 
		return "CommonPage";}

}
