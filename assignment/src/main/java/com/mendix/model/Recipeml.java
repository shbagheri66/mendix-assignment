package com.mendix.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="recipeml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Recipeml {

	private Recipe recipe;

    private String version;

    public Recipe getRecipe ()
    {
        return recipe;
    }

    public void setRecipe (Recipe recipe)
    {
        this.recipe = recipe;
    }

    public String getVersion ()
    {
        return version;
    }

    public void setVersion (String version)
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [recipe = "+recipe+", version = "+version+"]";
    }
}
