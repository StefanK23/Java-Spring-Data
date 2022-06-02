package com.example.XML.Exercise.ProductShop.Service;

import com.example.XML.Exercise.ProductShop.entities.Users.ExportSellersDTO;

public interface UserService {
    ExportSellersDTO findUserWithSoldProducts();
}
