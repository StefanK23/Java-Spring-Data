package com.example.XML.Exercise.ProductShop.entities.Category;

import javax.xml.bind.annotation.*;

// 	<category>
//		<name>Electronics</name>
//	</category>
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryNameDTO {

    @XmlElement
    private String name;

    public CategoryNameDTO() {
    }

    public String getName() {
        return name;
    }
}
