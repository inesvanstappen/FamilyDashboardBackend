package be.vsb.familydashboard.recipes;

import be.vsb.familydashboard.weekmenu.WeekMenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

@DataJpaTest
@Import(RecipeService.class)
class RecipeServiceIntegrationTest {
    private static final String RECIPE_TABLE = "recipes";

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private WeekMenuRepository weekMenuRepository;

    @BeforeEach
    void setUp() {
        weekMenuRepository.deleteAll();
        recipeRepository.deleteAll();

        Recipe seed = new Recipe("Seed", 2, Categorie.DESSERT);

        recipeRepository.save(seed);
    }

    @Test
    void getAllRecipesReturnsList() {
        List<Recipe> all = recipeService.getAllRecipes();

        assertThat(all).hasSize(1);
        assertThat(all.get(0).getName()).isEqualTo("Seed");
    }

    @Test
    void getRecipeByIdReturnsCorrectRecipe() {
        long id = recipeRepository.findByNameIgnoreCase("Seed").get().getId();

        assertThat(recipeService.getRecipeById(id))
                .isPresent()
                .get()
                .extracting(Recipe::getName)
                .isEqualTo("Seed");
    }

    @Test
    void getRecipeByIdReturnsEmptyForUnknownId() {
        assertThat(recipeService.getRecipeById(Long.MAX_VALUE)).isEmpty();
    }

    @Test
    void addRecipeAddsNewRecipe() {
        Recipe newRecipe = new Recipe("New", 1, Categorie.BREAKFAST);

        recipeService.addRecipe(newRecipe);

        assertThat(recipeRepository.findByNameIgnoreCase("New")).isPresent();
    }

    @Test
    void addDuplicateRecipeThrowsException() {
        Recipe duplicate = new Recipe("Seed", 2, Categorie.DESSERT);

        assertThatThrownBy(() -> recipeService.addRecipe(duplicate))
                .isInstanceOf(RecipeDuplicateException.class)
                .hasMessageContaining("Recipe with name Seed already exists");
    }

    @Test
    void deleteRecipeRemovesRecipe() {
        long id = recipeRepository.findByNameIgnoreCase("Seed").get().getId();

        recipeService.deleteRecipe(id);

        assertThat(recipeRepository.findById(id)).isEmpty();
    }

    @Test
    void deleteUnknownRecipeThrowsException() {
        assertThatThrownBy(() -> recipeService.deleteRecipe(Long.MAX_VALUE))
                .isInstanceOf(RecipeNotFoundException.class);
    }

    @Test
    void updateRecipeChangesFields() {
        long id = recipeRepository.findByNameIgnoreCase("Seed").get().getId();

        Recipe updated = new Recipe(id, "Updated", 3, Categorie.DINNER);

        recipeService.updateRecipe(id, updated);

        Recipe result = recipeRepository.findById(id).get();
        assertThat(result.getName()).isEqualTo("Updated");
        assertThat(result.getDifficulty()).isEqualTo(3);
        assertThat(result.getCategorie()).isEqualTo(Categorie.DINNER);
    }

    @Test
    void updateUnknownRecipeThrowsException() {
        Recipe dummy = new Recipe(Long.MAX_VALUE, "Ghost", 1, Categorie.BREAKFAST);

        assertThatThrownBy(() -> recipeService.updateRecipe(dummy.getId(), dummy))
                .isInstanceOf(RecipeNotFoundException.class);
    }
}
