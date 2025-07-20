package be.vsb.familydashboard.recipes;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void updateRecipe(long id, Recipe recipe) {
        recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);

        recipeRepository.save(recipe);
    }

    public void deleteRecipe(long id) {
        recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);

        recipeRepository.deleteById(id);
    }

    public void addRecipe(@Valid Recipe recipe) throws RecipeDuplicateException {
        recipeRepository.findByNameIgnoreCase(recipe.getName()).ifPresent(existingRecipe -> {
            throw new RecipeDuplicateException("Recipe with name " + recipe.getName() + " already exists.");
        });

        recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(long id) {
        return recipeRepository.findById(id);
    }
}
