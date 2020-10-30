package com.adillice.recipes.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adillice.recipes.models.Signin;
import com.adillice.recipes.models.User;
import com.adillice.recipes.services.LoginService;
import com.adillice.recipes.validators.LoginValidations;

@Controller
public class LoginController {

	//ini service
	protected final LoginValidations loginValidation;
	protected final LoginService loginService;

	//constructor injection
	public LoginController(LoginValidations loginValidation, LoginService loginService){
		this.loginValidation = loginValidation;
		this.loginService = loginService;
	}
	
	//SHOW:login and registration
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(@ModelAttribute("signin") Signin signin, @ModelAttribute("register") User register){
		
		//return login
		return "login";
	
	}

	//PROCESS:registration
	@RequestMapping(value="/register/process", method=RequestMethod.POST)
	public String registration(@Valid @ModelAttribute("register") User register, BindingResult br, @ModelAttribute("signin") Signin signin, RedirectAttributes ra, HttpServletRequest request, HttpSession session) {
		
		//check custom validation
		loginValidation.validate(register, br);
		
		//has errors
		if(br.hasErrors()) {
			
			//just go back
			return "login";
		
		//success
		}else {
			
			//save user to db
			loginService.registerUser(register);
			
			//clean up any existing sessions
			session.invalidate();			
			
			//setup new session
			HttpSession newSession = request.getSession();
			
			//pass user to new session
			newSession.setAttribute("user", register);
			
			//add success msg
			ra.addFlashAttribute("success", "You have successfully signed up, " + register.getUsername() + ".");
			
			//go to index on success
			return "redirect:/";
		
		}
		
	}

	//PROCESS:signing in
	@RequestMapping(value="/signin/process", method=RequestMethod.POST)
	public String signin(@Valid @ModelAttribute("signin") Signin signin, BindingResult br, @ModelAttribute("register") User register, Model model, RedirectAttributes ra, HttpServletRequest request, HttpSession session){
		
		//has errors
		if(br.hasErrors()){		
			
			//just go back
			return "login";
		
		//success
		}else{
			
			//try to authenticate user
			if(loginService.authenticateUser(signin.getEmail(), signin.getPassword())){

				//get user
				User thisUser = loginService.getUserByEmail(signin.getEmail());
				
				//clean up any existing sessions
				session.invalidate();
				
				//setup new session
				HttpSession newSession = request.getSession();
				
				//pass user to new session
				newSession.setAttribute("user", thisUser);
				
				//add success msg
				ra.addFlashAttribute("success", "You have successfully signed in " + thisUser.getUsername() + ".");
				
				//go to index on success
				return "redirect:/";
			
			//failed:authentication
			}else {
			
				//add fail msg
				model.addAttribute("errors", "Login attempt has failed.");
			
				//return to form
				return "login";
				
			}
		}
		
	}
	
}
