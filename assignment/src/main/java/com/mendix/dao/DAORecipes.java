package com.mendix.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.mendix.exception.DuplicateRecipeException;
import com.mendix.exception.InvalidInputException;
import com.mendix.model.Recipe;

@Repository
public class DAORecipes {
	private List<Recipe> recipes = new ArrayList<>();
	
	/**
	 * fetch recipes from sample XMLs
	 */
	@PostConstruct
	private void loader() {
		//TODO initial the list with sample XMLs
	}
	/**
	 * return all available recipes
	 * @return list of recipes
	 */
	public List<Recipe> getRecipes(){
		//TODO return all recipes
		return null;
	}
	
	/**
	 * add a new recipe
	 * @param recipe the new recipe
	 * @throws InvalidInputException if a parameter has invalid value
	 * @throws DuplicateRecipeException if another recipe with the same title exists
	 */
	public void addRecipe(Recipe recipe) throws InvalidInputException,DuplicateRecipeException {
		//TODO add recipe if there is no error
	}
	
}
