package com.hriday.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SanjayGandiZooPatna {
	
	
	@GetMapping("/go")
	public String LetUsGoZoo() {
		
		return "SANJAY GANDHI UDAYAAN PARK, PATNA";
	}
	
	
	@GetMapping("/ticket")
	public String entry()
	{
		
		return "WELCOME TO SANJAY GANDHI UDAYAAN PARK, PATNA";
	}
	
	
	@GetMapping("/train")
	public String safarWithTroolyTrain() {
		
		return "Enjoy ! You are doing SAFAR ZOO with TRAIN";
	}
	
	@GetMapping("/fish")
	public String machhaliGhar() {
		
		return "Enjoying ! Machhaliii Ghar";
	}
	
	@GetMapping("/snake")
	public String snakeGhar()
	{
		return "Enjoying ! with all type of SNAKE in thrilling";
	}
	
	
	@GetMapping("/hotel")
	public String lunchTime() {
		
		return "I was tired so need to have food to get power to safar rest of the places in the Zoo";
	}


}
