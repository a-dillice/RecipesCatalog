package com.adillice.recipes.services;

import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import com.adillice.recipes.models.User;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import com.adillice.recipes.repositories.LoginRepo;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class LoginService {

	//init injection
	protected final LoginRepo lRepo;
	
	//constructor injection
	public LoginService(LoginRepo lRepo) {
		this.lRepo = lRepo;
	}
	
	//get user by id
	public User getUserById(Long id){
		
		//try to find user by email
		Optional<User> thisUser = lRepo.findById(id);
		
		//return user
		return thisUser.isPresent() ? thisUser.get() : null;
		
	}
	
	//get one user by email
	public User getUserByEmail(String string) {
		
		//try to find user by email
		Optional<User> thisUser = lRepo.findByEmail(string);
		
		//return user
		return thisUser.isPresent() ? thisUser.get() : null;
		
	}
	
	//get one user by username
	public User getUserByUsername(String string) {
		
		//try to find user by email
		Optional<User> thisUser = lRepo.findByUsername(string);
		
		//return user
		return thisUser.isPresent() ? thisUser.get() : null;
		
	}	
	
	//save a registering user
	public void registerUser(User user){
		
		//we want to bcrypt the password
		String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        
		//set pass word to this user
		user.setPassword(encryptedPassword);
		
		//save user
		lRepo.save(user);
		
	}
	
	//authenticate user
	public boolean authenticateUser(String email, String password) {
		
		//get user from db
		User dbUser = getUserByEmail(email);
		
		//make sure email returned a user
		if(dbUser != null) {
			
			//check password provided with user password
			if(BCrypt.checkpw(password, dbUser.getPassword())){
				
				//success: return true
				return true;
			
			}
		
		}
		
		//failed: return false
		return false;
		
	}
	
	//only registered user access
	public String membersOnly(HttpSession session, RedirectAttributes ra) {
		
		//get user from session
		User logStatus = (User) session.getAttribute("user");
		
		//user is not logged in redirect to login page
		if(logStatus == null) {
			
			//error msg
			ra.addFlashAttribute("errors", "You must be logged in to view that page.");
			
			//return redirect
			return "redirect:/login";
			
		}
	
		//return null
		return null;
		
	}	
	
	//log user out
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	
}