package com.adillice.recipes.validators;

import com.adillice.recipes.models.Recipe;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import com.adillice.recipes.services.MainService;

@Component
public class RecipeValidators implements Validator{

	
	//init injection
	protected final MainService service;
	
	//constructor injection
	public RecipeValidators(MainService service) {
		this.service = service;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Recipe.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		//init user
		Recipe recipe = (Recipe) target;
		
		//check if category limit is valid (3 max)
		if(recipe.getCategories().split(",").length > 3) {
			
			//send error
			errors.rejectValue("categories", "length", "The category list must be 3 categories or less.");			
			
		}
		
		//check if file image is the correct format
		if((!recipe.getFile().getContentType().toLowerCase().equals("image/png"))&&
		   (!recipe.getFile().getContentType().toLowerCase().equals("image/jpeg"))&&
		   (!recipe.getFile().getContentType().toLowerCase().equals("image/gif"))){
			
			//send error
			errors.rejectValue("file", "Unique", "The image must be a .jpg, .jpeg, .gif, or a .png content type.");
			
		}
		
		//image size limit
		//get file size	
		double fileSize = recipe.getFile().getSize()/(1024 * 1024);
			
		//check if file is greater than 1mb
		if(fileSize > 1d){
			
			//send error
			errors.rejectValue("file", "Limit", "The image must be less than 1MB.");
			
		}		
		
		
	}

	
	
}
