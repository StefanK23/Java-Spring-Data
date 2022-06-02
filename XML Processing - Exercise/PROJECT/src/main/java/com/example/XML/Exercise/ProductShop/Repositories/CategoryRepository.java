package com.example.XML.Exercise.ProductShop.Repositories;


import com.example.XML.Exercise.ProductShop.entities.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
