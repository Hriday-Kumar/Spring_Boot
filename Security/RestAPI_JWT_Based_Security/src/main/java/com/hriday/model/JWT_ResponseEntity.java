package com.hriday.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWT_ResponseEntity {

	
	private String status;
	private String token;
}
