package com.myapp.myapp.Security;

public class AuthenticationResponse {
	
	
	 private  String jwt;
	 private int userId ;
	 
	 public AuthenticationResponse(String jwt , int userId ) {
		 
		 this.jwt=jwt ;
		 this.userId = userId ; 
	 }

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
