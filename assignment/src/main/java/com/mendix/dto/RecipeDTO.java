package com.mendix.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mendix.model.Ing;
import com.mendix.model.Recipe;

public class RecipeDTO {
	private String title;
    private String yield;
    private List<String> categoryList;
    private String direction;
    private List<String> ingredients;
    
    public RecipeDTO() {
	}
    
    public RecipeDTO(Recipe recipe) {
    	if(recipe!=null) {
			setTitle(recipe.getHead().getTitle());
			setYield(recipe.getHead().getYield());
			setCategoryList(Arrays.asList(recipe.getHead().getCategories().getCat()));
			setDirection(recipe.getDirections().getStep());
			Ing[] ingredients = recipe.getIngredients().getIng();
			List<String> ingredientList = new ArrayList<>();
			for (Ing ing : ingredients) {
				String ingredientStr = "";
				if(ing.getAmt()!=null) {
					if(ing.getAmt().getQty()!=null)
						ingredientStr+=ing.getAmt().getQty()+" ";
					if(ing.getAmt().getUnit()!=null)
						ingredientStr+=ing.getAmt().getUnit()+" ";
					if(ing.getItem()!=null)
						ingredientStr+=ing.getItem();
				}
				ingredientList.add(ingredientStr);
			}
			setIngredients(ingredientList);
    	}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
}
