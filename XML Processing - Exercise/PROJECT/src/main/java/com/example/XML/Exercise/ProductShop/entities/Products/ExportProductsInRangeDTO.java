package com.example.XML.Exercise.ProductShop.entities.Products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportProductsInRangeDTO {

    @XmlElement(name = "product")
    List<ProductWithAttributesDTO> products;

    public ExportProductsInRangeDTO(){}

    public ExportProductsInRangeDTO(List<ProductWithAttributesDTO> products) {
        this.products = products;
    }

    public List<ProductWithAttributesDTO> getProducts() {
        return products;
    }
}
