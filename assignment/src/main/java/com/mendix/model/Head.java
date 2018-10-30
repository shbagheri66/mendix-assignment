package com.mendix.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="head")
@XmlAccessorType(XmlAccessType.FIELD)
public class Head {
	@XmlElement(name="title")
	private String title;

	@XmlElement(name="yield")
    private String yield;

	@XmlElement(name="categories")
    private Categories categories;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getYield ()
    {
        return yield;
    }

    public void setYield (String yield)
    {
        this.yield = yield;
    }

    public Categories getCategories ()
    {
        return categories;
    }

    public void setCategories (Categories categories)
    {
        this.categories = categories;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", yield = "+yield+", categories = "+categories+"]";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Head other = (Head) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
    
    
}
