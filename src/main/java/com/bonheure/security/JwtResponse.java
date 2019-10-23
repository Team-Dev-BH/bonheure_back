package com.bonheure.security;

public class JwtResponse {
	private String email;
	private String role;
	private String token;
	 
	 

	public JwtResponse(String token, String email, String authoritiy  ) {

		this.email = email;
		this.role = authoritiy;
		this.token = token;
		 
 
	}

 

	 



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getrole() {
		return role;
	}

	public void setrole(String role) {
		this.role = role;
	}

}
