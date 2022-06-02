package com.example.XML.Exercise.ProductShop.entities.Category;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryImportDTO {

    @XmlElement(name = "category")
    private List<CategoryNameDTO> categories;

    public CategoryImportDTO(){}

    public List<CategoryNameDTO> getCategories() {
        return categories;
    }
}
