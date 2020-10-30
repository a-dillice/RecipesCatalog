package com.adillice.recipes.controllers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adillice.recipes.models.Recipe;
import com.adillice.recipes.models.User;
import com.adillice.recipes.services.MainService;
import com.adillice.recipes.validators.RecipeValidators;

@Controller
public class MainController {

	//image upload path
	private final String imgDir = "E:/apache/htdocs/codingDojo/JAVA/OOP/spring/recipes/src/main/resources/static/images/recipes/"; 

	//init injection
	protected final MainService service;
	protected final RecipeValidators rValid;
	
	//constructor injection
	public MainController(MainService service, RecipeValidators recipeValidation){
		this.rValid = recipeValidation;
		this.service = service;
	}	
	
	//index page
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model, HttpSession session) {
		
		//add session data to page
		model.addAttribute("session", (User) session.getAttribute("user"));
		
		//pass recipes to page
		model.addAttribute("recipes", service.getAllRecipes());
		
		//return
		return "index";
	
	}

	//search
	@RequestMapping(value="/search/results", method=RequestMethod.POST)
	public String search(@RequestParam("search") String search, Model model, HttpSession session, RedirectAttributes ra) {
		
		//get search results
		List<Recipe> results = service.searchAllRecipes(search);
		
		//no results go home
		if(results == null || results.size() < 1) {
			
			//so search fail msg
			ra.addFlashAttribute("errors", "Your search yielded no results. Please try again.");
			
			//return 
			return "redirect:/";
		}
		
		//so search success msg
		String msg = (results.size() > 1) ? "Your search for '" + search + "' resulted in " + results.size() + " recipes." : "Your search for '" + search + "' resulted in 1 recipe.";  
		model.addAttribute("success", msg);
		
		//add session data to page
		model.addAttribute("session", (User) session.getAttribute("user"));
		
		//pass recipes to page
		model.addAttribute("recipes", service.searchAllRecipes(search));
		
		//return index
		return "index";
	
	}
	
	//MEMBERS ONLY: SHOW:add new recipe 
	@RequestMapping(value="/recipes/new", method=RequestMethod.GET)
	public String addRecipe(@ModelAttribute("recipe") Recipe recipe, Model model, HttpSession session, RedirectAttributes ra) {
		
		//check access
		String access = service.access(session, ra);
		if(access != null){ return access; }
		
		//add session data to page
		model.addAttribute("session", (User) session.getAttribute("user"));		
		
		//return
		return "add";
	
	}	
	
	//MEMBERS ONLY: PROCESS:add new recipe
	@RequestMapping(value="/recipes/new/process", method=RequestMethod.POST, consumes={"multipart/form-data"})
	public String addRecipeProcess(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult br, Model model, HttpSession session, RedirectAttributes ra){
		
		//check access
		String access = service.access(session, ra);
		if(access != null){ return access; }
		
		//get current user
		User thisUser = (User) session.getAttribute("user");
		
		//add session data to page
		model.addAttribute("session", thisUser);
		
		//validate
		rValid.validate(recipe, br);
		
		//errors
		if(br.hasErrors()){
			
			//return
			return "add";
			
		//redirect
		}else{
			
			//try
			try{
				
				//1.add file name to model image var
				
				//get ext
				String[] ext = recipe.getFile().getContentType().split("/");
				
				//setup random filename
				Random random = new Random();
				String newName = "recipe" + random.nextInt(999) + System.currentTimeMillis() + random.nextInt(999) + "." + ext[ext.length - 1];
				
				//2.copy file from input stream
				Files.copy(recipe.getFile().getInputStream(), 
						Paths.get(imgDir + newName), 
						StandardCopyOption.REPLACE_EXISTING);
						
				//3.save data to db
				//set name to recipe model
				recipe.setImage(newName);				
				
				//set user to recipe
				recipe.setUser(thisUser);
				
				//save to db
				service.saveRecipe(recipe);
				
				//success msg
				ra.addFlashAttribute("success", "You have successfully uploaded the '" + recipe.getTitle() + "' recipe.");
				
			//catch 	
			}catch(Exception e){
				
				//send error
				br.rejectValue("file", "exception", e.toString());
				
			}
			
			//return
			return "redirect:/recipes/new";
			
		}

		
	}	
	
	//show one recipe
	@RequestMapping(value="/recipe/show/{id}", method=RequestMethod.GET)
	public String showRecipe(@PathVariable("id") Long id, Model model, HttpSession session){
		
		//add session data to page
		model.addAttribute("session", (User) session.getAttribute("user"));	
		
		//pass recipe to page
		model.addAttribute("recipe", service.oneRecipe(id));
		
		//go to show
		return "show";
		
	}
	
	//logout
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		//logout service method
		service.logout(session);
		
		//return index
		return "redirect:/login";
		
	}	
	
}
