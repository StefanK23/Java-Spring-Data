package com.example.advquerying;

import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

private final ShampooService shampooService;
private final ShampooRepository shampooRepository;
private final IngredientService ingredientService;

    @Autowired
    public Runner(
            ShampooService shampooService
            , ShampooRepository shampooRepository, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.shampooRepository = shampooRepository;
        this.ingredientService = ingredientService;
    }



    @Override
    public void run(String... args)  {

    }




    private void methods(){
        this.shampooService.selectBySize(Size.MEDIUM)
                .forEach(System.out::println);

        this.shampooService.selectBySizeOrLabelId(Size.MEDIUM,10)
                .forEach(System.out::println);

        this.shampooService.selectMoreExpensiveThan(BigDecimal.valueOf(5))
                .forEach(System.out::println);

        this.ingredientService.selectNameStartsWith("M")
                .forEach(System.out::println);

        this.ingredientService.selectInName(List.of("Lavender","Herbs","Apple" ))
                .forEach(System.out::println);

        int count = this.shampooService.countPriceLowerThan(BigDecimal.valueOf(8.50));
        System.out.println(count);
    }
}
