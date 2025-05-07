package com.klef.fsad.sdp.dto;

public class LoginRequest {
	
	private String identifier; // email or username
	private String password;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LoginRequest [identifier=" + identifier + ", password=" + password + "]";
	} 
}
