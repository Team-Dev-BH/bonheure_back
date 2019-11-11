package com.bonheure.security;

public class JwtResponse {
	private String username;
	private String role;
	private String token;
	 
	 


	public JwtResponse(String token, String username, String authoritiy) {

		this.username = username;
		this.role = authoritiy;
		this.token = token;
		 
 
	}

 

	 



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getrole() {
		return role;
	}

	public void setrole(String role) {
		this.role = role;
	}

}
