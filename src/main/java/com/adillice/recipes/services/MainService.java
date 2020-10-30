package com.adillice.recipes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.adillice.recipes.models.Category;
import com.adillice.recipes.models.Recipe;
import com.adillice.recipes.repositories.CategoryRepo;
import com.adillice.recipes.repositories.RecipeRepo;

@Service
public class MainService {

	//init inject
	protected final LoginService login;
	protected final CategoryRepo catRepo;
	protected final RecipeRepo recRepo;
	
	//constructor injection
	public MainService(LoginService login, CategoryRepo catRepo, RecipeRepo recRepo) {
		this.catRepo = catRepo;
		this.recRepo = recRepo;
		this.login = login;
	}
	
	//access
	public String access(HttpSession session, RedirectAttributes ra) {
		
		//return where to go
		return login.membersOnly(session, ra);
	
	}
	
	//use login method thru this service
	public void logout(HttpSession session) {
		
		//logout
		login.logout(session);
		
	}	
	
	//get all recipes
	public List<Recipe> getAllRecipes(){
		return (List<Recipe>) recRepo.findAll();
	}
	
	//get one recipe
	public Recipe oneRecipe(Long id){
		
		//grab recipe
		Optional<Recipe> thisRecipe = recRepo.findById(id);
		
		//return
		return (thisRecipe.isPresent()) ? thisRecipe.get() : null;
		
	}
	
	//save recipe
	public void saveRecipe(Recipe recipe){
		
		//init new list for categories
		List<Category> newCategories = new ArrayList<Category>();
		
		//get strings category
		String[] individualCategories = recipe.getCategories().split(",");
		
		//loop thru our form given categories
		for(String cat : individualCategories) {
			
			//trim the string
			String catClean = cat.trim();
			
			//check for existing category
			Category currentCategory = catRepo.findByNameEquals(catClean);
			
			//check if we do NOT have the category in our db table
			if(currentCategory == null) {
				
				//create new category and set it to currentCategory
				currentCategory = new Category();
				
				//add string from individualCategories to our current category object
				currentCategory.setName(catClean);
				
			}
			
			//add currentCategory to list
			newCategories.add(currentCategory);
			
		}
		
		//save categories
		recipe.setCategorys(newCategories);
		
		//save recipe
		recRepo.save(recipe);
	
	}
	
	//search recipes
	public List<Recipe> searchAllRecipes(String search){
		
		//make sure we have a value
		if(search == "" || search == null) {
			return null;
		}
		
		return recRepo.searchRecipes(search);
	}
	
	
}
