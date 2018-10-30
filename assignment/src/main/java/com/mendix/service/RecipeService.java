package com.mendix.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendix.dao.DAORecipes;
import com.mendix.exception.DuplicateRecipeException;
import com.mendix.exception.InvalidInputException;
import com.mendix.model.Recipe;

/**
 * Needed rest services for Mendix assignment,recipe part
 * @author Shahab Bagheri
 */
@RestController
@RequestMapping("/public/recipe")
public class RecipeService {
	
	@Autowired
	DAORecipes daoRecipes;

	Logger logger = Logger.getLogger(getClass().getName());
	/**
	 * fetch all available recipes from memory
	 * @return list of all recipes
	 */
	@GetMapping
	public ResponseEntity<List<Recipe>> getAllRecipe()
	{
		List<Recipe> result = daoRecipes.getRecipes();
		logger.trace("Finished,size:"+result.size());
		return ResponseEntity.ok(result);
	}
	
	/**
	 * fetch all available recipes containing special text in categories or title
	 * @param query the search String query
	 * @return list of recipes that contain special text
	 */
	@GetMapping("/search/{query}")
	public ResponseEntity<List<Recipe>> search(@PathVariable("query") String query)
	{
		List<Recipe> recipes = daoRecipes.getRecipes();
		List<Recipe> result = new ArrayList<>();
		for (Recipe recipe : recipes) {
			if(recipe.getHead().getTitle().toLowerCase().contains(query.toLowerCase())) {
				result.add(recipe);
			}else {
				for (String cat : recipe.getHead().getCategories().getCat()) {
					if(cat.toLowerCase().contains(query.toLowerCase())) {
						result.add(recipe);
						break;
					}
				}
			}
		}
		logger.trace("Finished,size:"+result.size());
		return ResponseEntity.ok(result);
	}
	/**
	 * search in all available recipes and return those that are in special category
	 * @param category the category name to return all related recipes
	 * @return list of recipes that are in special category
	 */
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Recipe>> getByCategory(@PathVariable("category") String category)
	{
		List<Recipe> recipes = daoRecipes.getRecipes();
		List<Recipe> result = new ArrayList<>();
		for (Recipe recipe : recipes) {
			for (String cat : recipe.getHead().getCategories().getCat()) {
				if(cat.equalsIgnoreCase(category)) {
					result.add(recipe);
					break;
				}
			}
		}
		logger.trace("Finished,size:"+result.size());
		return ResponseEntity.ok(result);
	}
	
	/**
	 * add new recipe
	 * @param new recipe in JSON format
	 * @return OK if recipe added successfully other wise return bad request with information on error's type
	 * @throws URISyntaxException 
	 */
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Recipe recipe) throws URISyntaxException
	{
		try {
			daoRecipes.addRecipe(recipe);
			logger.trace("Finished,title:"+recipe.getHead().getTitle());
			return ResponseEntity.created(new URI(Base64.getEncoder().encodeToString(recipe.getHead().getTitle().getBytes()))).body(recipe);
		} catch (InvalidInputException | DuplicateRecipeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}

