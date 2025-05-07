package com.klef.fsad.sdp.model;

public class ForgotPassword {
	
	private String email;
	private String newPassword;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	@Override
	public String toString() {
		return "ForgotPassword [email=" + email + ", newPassword=" + newPassword + "]";
	}
	
	
}
