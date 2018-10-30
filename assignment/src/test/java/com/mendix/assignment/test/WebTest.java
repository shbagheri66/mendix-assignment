package com.mendix.assignment.test;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import com.google.gson.Gson;
import com.mendix.model.Amt;
import com.mendix.model.Categories;
import com.mendix.model.Directions;
import com.mendix.model.Head;
import com.mendix.model.Ing;
import com.mendix.model.Ingredients;
import com.mendix.model.Recipe;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebTest {
	
	static HttpSession session = null;

	@Autowired
	protected MockMvc mockMvc;
	
	@Test
	public void a_getAllTest() throws Exception {
		this.mockMvc.perform(get("/public/recipe"))
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void b_searchWithSearchQueryTest() throws Exception {
		this.mockMvc.perform(get("/public/recipe/search/chili"))
		.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				assertTrue(result.getResponse().getContentAsString().toLowerCase().contains("chili"));
			}
		})
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void c_searchWithCategoryTest() throws Exception {
		this.mockMvc.perform(get("/public/recipe/category/vegetables"))
		.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				assertTrue(result.getResponse().getContentAsString().toLowerCase().contains("vegetables"));
			}
		})
		.andExpect(status().is2xxSuccessful());
	}
	
	
	@Test
	public void d_addTest() throws Exception {
		this.mockMvc.perform(post("/public/recipe/add")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new Gson().toJson(getMockRecipe())))
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void e_addWithDuplicateNameTest() throws Exception {
		this.mockMvc.perform(post("/public/recipe/add")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new Gson().toJson(getMockRecipe())))
		.andExpect(status().isBadRequest());
	}
	
	
	private Recipe getMockRecipe() {
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
