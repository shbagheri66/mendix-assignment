package com.mendix.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mendix.exception.DuplicateRecipeException;
import com.mendix.exception.InvalidInputException;
import com.mendix.model.Recipe;
import com.mendix.model.Recipeml;

@Repository
public class DAORecipes {
	private List<Recipe> recipes = new ArrayList<>();
	private Logger logger = Logger.getLogger(getClass().getName());
	
	/**
	 * fetch recipes from sample XMLs
	 */
	@PostConstruct
	private void loader() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Recipeml.class);
		
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		String[] allXMLFiles = new File(getClass().getResource("/xml/").getFile()).list();
		
		for (String xmlPath : allXMLFiles) {
			Recipeml recipml = (Recipeml) unmarshaller.unmarshal(new File(getClass().getResource("/xml/" + xmlPath).getFile()));
			try {
				addRecipe(recipml.getRecipe());
				logger.info(String.format("XMl file:%s loaded", xmlPath));
			} catch (InvalidInputException | DuplicateRecipeException e) {
				logger.error(e.getMessage());
			}
		}
	}
	/**
	 * return all available recipes
	 * @return list of recipes
	 */
	public List<Recipe> getRecipes() {
		return recipes;
	}
	
	/**
	 * add a new recipe
	 * @param recipe the new recipe
	 * @throws InvalidInputException if a parameter has invalid value
	 * @throws DuplicateRecipeException if another recipe with the same title exists
	 */
	public synchronized void addRecipe(Recipe recipe) throws InvalidInputException,DuplicateRecipeException {
		String canNotBeNullOrEmptyError = "can not be empty";
		if(recipe==null) {
			throw new InvalidInputException("can not add null recipe");
		}else if(recipe.getHead()==null) {
			throw new InvalidInputException("head", canNotBeNullOrEmptyError);
		}else if(StringUtils.isEmpty(recipe.getHead().getTitle())) {
			throw new InvalidInputException("title", canNotBeNullOrEmptyError);
		}else if(recipe.getHead().getCategories()==null || recipe.getHead().getCategories().getCat()==null || recipe.getHead().getCategories().getCat().length==0) {
			throw new InvalidInputException("category", canNotBeNullOrEmptyError);
		}else if(StringUtils.isEmpty(recipe.getHead().getYield())) {
			throw new InvalidInputException("yield", canNotBeNullOrEmptyError);
		}else if(recipe.getDirections()==null || StringUtils.isEmpty(recipe.getDirections().getStep())) {
			throw new InvalidInputException("direction", canNotBeNullOrEmptyError);
		}else if(recipe.getIngredients()==null || recipe.getIngredients().getIng()==null || recipe.getIngredients().getIng().length==0) {
			throw new InvalidInputException("ingredients", canNotBeNullOrEmptyError);
		}else if(recipes.contains(recipe)) {
			throw new DuplicateRecipeException(String.format("Recipe alreadyExists : %s", recipe.getHead().getTitle()));
		}else {
			recipes.add(recipe);
		}
	}
	
}
