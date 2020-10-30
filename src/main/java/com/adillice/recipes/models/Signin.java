package com.adillice.recipes.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//signin class for our login form
//wont be saved in the database
public class Signin{

	@NotEmpty(message="Email is required.")
	@Email(message="The email is invalid.")
	private String email;
	
	@NotEmpty(message="Password is required.")
	@Size(min=8, message="Password must be at least 8 charcters long.")
	private String password;
	
	//getters/setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
