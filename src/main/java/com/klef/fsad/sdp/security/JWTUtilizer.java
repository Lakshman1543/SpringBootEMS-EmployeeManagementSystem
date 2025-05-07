package com.klef.fsad.sdp.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTUtilizer {
	
	private final String SECRET_KEY_STRING = "eyJhcGkta2V5IjogIlY0TkpDd0V5NFltZ1JEZDUwcHFlM2hkdmJaR0dqV1VuIn0=";
	private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
	
	public String generateJWTToken(String username, String role) {
		Map<String, Object> mp = new HashMap<>(); // we created a Map to hold custom data that we want to include in the JWT
		mp.put("username", username);  // Username
		mp.put("role", role);  // User Role (Admin/Manager/Employee)
		
		return Jwts.builder()
				.setClaims(mp)  // -> Attaches your custom payload 
				.setSubject(username) // A general field for identifying the token (it can be used for username).
				.setIssuedAt(new Date()) // when the token was created
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2)) // when token is going to expire (2 hours)
				.signWith(key, SignatureAlgorithm.HS256) //Secures the token with our secret key
				.compact(); // it is a final step to return the token as a string
	}
	
	public Map<String, String> validateToken(String token) {
		Map<String, String> res = new HashMap<>();
		try {
			Claims c = Jwts.parserBuilder() // used to configure the parser
					          .setSigningKey(key) // Same  key which used for generation (Secret key that used to verify)
					          .build()
					          .parseClaimsJws(token) // it verifies and parses token
					          .getBody(); // returns the actual data inside the token
			
			res.put("username", c.get("username", String.class));
			res.put("role", c.get("role", String.class));
			res.put("code", "200");
		}
		catch(ExpiredJwtException e) {
			res.put("code", "401");
			res.put("error", "token expired. Please login again");
			// res.put("error", e.getMessage());
		}
		return res;
	}
}
