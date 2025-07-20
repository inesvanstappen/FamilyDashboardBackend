package be.vsb.familydashboard.recipes;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("{id}")
    public Recipe getRecipeById(@PathVariable long id) {
        return recipeService.getRecipeById(id).orElseThrow(RecipeNotFoundException::new);
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addRecipe(@RequestBody @Valid Recipe recipe) {
        recipeService.addRecipe(recipe);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecipe(@PathVariable long id) {
        recipeService.deleteRecipe(id);
    }

    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRecipe(@PathVariable long id, @RequestBody @Valid Recipe recipe) {
        recipeService.updateRecipe(id, recipe);
    }
}
