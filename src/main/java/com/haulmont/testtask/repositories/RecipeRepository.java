package com.haulmont.testtask.repositories;

import com.haulmont.testtask.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}