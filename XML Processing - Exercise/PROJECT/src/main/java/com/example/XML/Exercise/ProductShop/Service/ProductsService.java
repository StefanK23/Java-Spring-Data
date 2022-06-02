package com.example.XML.Exercise.ProductShop.Service;

import com.example.XML.Exercise.ProductShop.entities.Products.ExportProductsInRangeDTO;

public interface ProductsService {
    ExportProductsInRangeDTO getInRange(float from, float to);
}
