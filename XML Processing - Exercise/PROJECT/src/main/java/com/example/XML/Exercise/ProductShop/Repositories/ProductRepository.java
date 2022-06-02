package com.example.XML.Exercise.ProductShop.Repositories;

import com.example.XML.Exercise.ProductShop.entities.Products.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(BigDecimal rangeFrom, BigDecimal rangeTo);
}
