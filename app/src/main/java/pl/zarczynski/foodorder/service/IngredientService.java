package pl.zarczynski.foodorder.service;

import pl.zarczynski.foodorder.domain.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> listAllIngredients();
}
