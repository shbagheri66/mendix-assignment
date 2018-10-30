package com.mendix.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendix.dao.DAORecipes;
import com.mendix.model.Recipe;

/**
 * Needed rest services for Mendix assignment,category part
 * @author Shahab Bagheri
 */
@RestController
@RequestMapping("/public/category")
public class CategoryService {
	Logger logger = Logger.getLogger(getClass().getName());
	@Autowired
	DAORecipes daoRecipes;
	/**
	 * fetch all available categories from memory
	 * @return list of all categories
	 */
	@GetMapping
	public ResponseEntity<List<String>> getAll()
	{
		List<Recipe> recipes = daoRecipes.getRecipes();
		List<String> allCategories = new ArrayList<>();
		for (Recipe recipe : recipes) {
			for (String category : recipe.getHead().getCategories().getCat()) {
				if(!allCategories.contains(category)) {
					allCategories.add(category);
				}
			}
		}
		Collections.sort(allCategories);
		logger.trace("Finished,size:"+allCategories.size());
		return ResponseEntity.ok(allCategories);
	}
}

