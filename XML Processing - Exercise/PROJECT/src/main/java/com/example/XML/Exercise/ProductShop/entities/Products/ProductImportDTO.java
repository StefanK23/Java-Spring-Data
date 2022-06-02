package com.example.XML.Exercise.ProductShop.entities.Products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportDTO {

    @XmlElement
    private String name;

    @XmlElement
    private double price;


    public ProductImportDTO() {}

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
