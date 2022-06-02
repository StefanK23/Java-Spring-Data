package com.example.jsonEX.ProductShop.Repositories;

import com.example.jsonEX.ProductShop.entities.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
