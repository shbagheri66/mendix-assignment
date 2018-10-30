package com.mendix.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="recipe")
@XmlAccessorType(XmlAccessType.FIELD)
public class Recipe {
	private Ingredients ingredients;

    private Directions directions;

    private Head head;

    public Ingredients getIngredients ()
    {
        return ingredients;
    }

    public void setIngredients (Ingredients ingredients)
    {
        this.ingredients = ingredients;
    }

    public Directions getDirections ()
    {
        return directions;
    }

    public void setDirections (Directions directions)
    {
        this.directions = directions;
    }

    public Head getHead ()
    {
        return head;
    }

    public void setHead (Head head)
    {
        this.head = head;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ingredients = "+ingredients+", directions = "+directions+", head = "+head+"]";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		return true;
	}
    
    
}
