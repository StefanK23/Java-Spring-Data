package com.example.XML.Exercise.ProductShop.entities.Users;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportSellersDTO {

    @XmlElement(name = "user")
    List<ExportUserWithSoldProductDTO> users ;

public ExportSellersDTO( ){}

    public ExportSellersDTO(List<ExportUserWithSoldProductDTO> users) {
        this.users = users;
    }
}
