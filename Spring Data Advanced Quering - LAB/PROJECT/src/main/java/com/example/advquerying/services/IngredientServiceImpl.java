package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> selectNameStartsWith(String start) {
        return ingredientRepository.findByNameStartingWith(start);
    }

    @Override
    public List<Ingredient> selectInName(List<String> names) {
        return this.ingredientRepository.findByNameInOrderByPriceAsc(names);
    }
}
