package com.mendix.assignment.test;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mendix.dao.DAORecipes;
import com.mendix.exception.DuplicateRecipeException;
import com.mendix.exception.InvalidInputException;
import com.mendix.model.Amt;
import com.mendix.model.Categories;
import com.mendix.model.Directions;
import com.mendix.model.Head;
import com.mendix.model.Ing;
import com.mendix.model.Ingredients;
import com.mendix.model.Recipe;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DAORecipes.class)
public class DAORecipesTest {
	
	@Autowired
	DAORecipes daoRecipes;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	String invalidInputErrorFormat = "Error in field %s : %s";
	String canNotBeEmptyError = "can not be empty";

	
	@Test
	public void testLoadXML() {
		assertTrue(daoRecipes.getRecipes()!=null && daoRecipes.getRecipes().size()>0);
	}
	
	@Test(expected=DuplicateRecipeException.class)
	public void testAddDuplicateName() throws DuplicateRecipeException, InvalidInputException{
		Recipe recipe = getSampleRecipe();
		daoRecipes.addRecipe(recipe);
		daoRecipes.addRecipe(recipe);
	}
	
	@Test
	public void testAddNullHead() throws DuplicateRecipeException, InvalidInputException{
		expectedEx.expect(InvalidInputException.class);
	    expectedEx.expectMessage(String.format(invalidInputErrorFormat,"head",canNotBeEmptyError));
	    Recipe recipe = getSampleRecipe();
	    recipe.setHead(null);
		daoRecipes.addRecipe(recipe);
	}
	
	@Test
	public void testAddNullTitle() throws DuplicateRecipeException, InvalidInputException{
		expectedEx.expect(InvalidInputException.class);
	    expectedEx.expectMessage(String.format(invalidInputErrorFormat,"title",canNotBeEmptyError));
	    Recipe recipe = getSampleRecipe();
	    recipe.getHead().setTitle(null);
		daoRecipes.addRecipe(recipe);
	}
	
	@Test
	public void testAddNullCategory() throws DuplicateRecipeException, InvalidInputException{
		expectedEx.expect(InvalidInputException.class);
	    expectedEx.expectMessage(String.format(invalidInputErrorFormat,"category",canNotBeEmptyError));
	    Recipe recipe = getSampleRecipe();
	    recipe.getHead().setCategories(null);
		daoRecipes.addRecipe(recipe);
	}
	
	@Test
	public void testAddNullYield() throws DuplicateRecipeException, InvalidInputException{
		expectedEx.expect(InvalidInputException.class);
	    expectedEx.expectMessage(String.format(invalidInputErrorFormat,"yield",canNotBeEmptyError));
	    Recipe recipe = getSampleRecipe();
	    recipe.getHead().setYield(null);
		daoRecipes.addRecipe(recipe);
	}
	
	@Test
	public void testAddNullDirection() throws DuplicateRecipeException, InvalidInputException{
		expectedEx.expect(InvalidInputException.class);
	    expectedEx.expectMessage(String.format(invalidInputErrorFormat,"direction",canNotBeEmptyError));
	    Recipe recipe = getSampleRecipe();
	    recipe.setDirections(null);
		daoRecipes.addRecipe(recipe);
	}
	
	@Test
	public void testAddNullIngredients() throws DuplicateRecipeException, InvalidInputException{
		expectedEx.expect(InvalidInputException.class);
	    expectedEx.expectMessage(String.format(invalidInputErrorFormat,"ingredients",canNotBeEmptyError));
	    Recipe recipe = getSampleRecipe();
	    recipe.setIngredients(null);
		daoRecipes.addRecipe(recipe);
	}
	
	
	private Recipe getSampleRecipe() {
		Categories categories = new Categories();
		categories.setCat(new String[]{"first cat","second cat"});

		Head head = new Head();
		head.setTitle("new recipe");
		head.setYield("3");
		head.setCategories(categories);
		
		Amt amt = new Amt();
		amt.setQty("1");
		amt.setUnit("pound");

		Ing ing = new Ing();
		ing.setItem("an item");
		ing.setAmt(amt);
		
		Ingredients ingredients = new Ingredients();
		ingredients.setIng(new Ing[] {ing});
		
		Directions directions = new Directions();
		directions.setStep("step 1");
				
		Recipe recipe = new Recipe();
		recipe.setIngredients(ingredients);
		recipe.setHead(head);
		recipe.setDirections(directions);
		
		return recipe;
	}
	
}
