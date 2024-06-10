package com.hriday.util;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWT_Encoder_Decoder {

	@Value("${app.secret}")
	String secretKey;
	
	//Code for generate JWT token i.e encode JWT token.
	public String getJsonWebToken_JWT(String subject) {
		
		String secretKey = "Hriday";
		
		String token = 	Jwts.builder()
							.setId("45230116")
							.setSubject(subject)
							.setIssuer("Kumar")
							.setIssuedAt(new Date(System.currentTimeMillis()))
							.setExpiration(new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(2)))
							.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secretKey.getBytes()))
							.compact();
		
		return token;
	}
	
	public Claims getClaims_DecodeJWT(String token) {
		//Parsing or decode the token.
				Claims claims = Jwts.parser()
									.setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
									.parseClaimsJws(token)
									.getBody();
				
				System.out.println("Id : "+claims.getId());
				System.out.println("Subject : "+claims.getSubject());
				System.out.println("Issuer : "+claims.getIssuer());
				System.out.println("Issue Date : "+claims.getIssuedAt());
				System.out.println("Issued At : "+claims.getIssuedAt());
				System.out.println("Expirataion : "+claims.getExpiration());
				System.out.println("Token inactive : "+claims.getExpiration().before(new Date(System.currentTimeMillis())));
			
		return claims;
	}
	
	public boolean validateToken_SubjectAgainstUser(String token, String username)
	{
		String subject = getClaims_DecodeJWT(token).getSubject();
		return (subject.equals(username) && !(getClaims_DecodeJWT(token).getExpiration().before(new Date(System.currentTimeMillis()))));
		
	}
}
