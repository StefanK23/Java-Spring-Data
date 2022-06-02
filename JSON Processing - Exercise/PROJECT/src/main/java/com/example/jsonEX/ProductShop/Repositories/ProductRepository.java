package com.example.jsonEX.ProductShop.Repositories;

import com.example.jsonEX.ProductShop.entities.Products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
