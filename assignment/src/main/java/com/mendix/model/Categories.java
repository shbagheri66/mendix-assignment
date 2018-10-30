package com.mendix.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="cat")
@XmlAccessorType(XmlAccessType.FIELD)
public class Categories {
	private String[] cat;

    public String[] getCat ()
    {
        return cat;
    }

    public void setCat (String[] cat)
    {
        this.cat = cat;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cat = "+cat+"]";
    }
}
