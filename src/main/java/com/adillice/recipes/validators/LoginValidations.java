package com.adillice.recipes.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adillice.recipes.models.User;
import com.adillice.recipes.services.LoginService;

@Component
public class LoginValidations implements Validator{

	//init injection
	protected final LoginService service;
	
	//constructor injection
	public LoginValidations(LoginService service) {
		this.service = service;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		//init user
		User user = (User) target;
		
		//check if user email already exists
		if(service.getUserByEmail(user.getEmail()) != null) {
			
			//send error
			errors.rejectValue("email", "Unique", "That email is already taken.");
			
		}

		//check if username already exists
		if(service.getUserByUsername(user.getUsername()) != null) {
			
			//send error
			errors.rejectValue("username", "Unique", "That user name is already taken.");
			
		}
		
		//check password equals confirm 
		if(!user.getConfirm().equals(user.getPassword())) {
			
			//send out error
			errors.rejectValue("confirm", "Match", "Password and confirm password must match.");
			
		}
		
	}

}
