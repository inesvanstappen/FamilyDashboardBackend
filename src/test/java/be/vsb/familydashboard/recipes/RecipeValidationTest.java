package be.vsb.familydashboard.recipes;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeValidationTest {
    private static Validator validator;

    @BeforeAll
    static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private Set<String> violationMessages(Recipe recipe) {
        return validator.validate(recipe)
                .stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.toSet());
    }

    @Test
    void validRecipeHasNoViolations() {
        Recipe recipe = new Recipe("Tiramisu", 2, Categorie.DESSERT);
        assertThat(violationMessages(recipe)).isEmpty();
    }

    @Test
    void blankNameTriggersViolation() {
        Recipe recipe = new Recipe("", 2, Categorie.DESSERT);
        assertThat(violationMessages(recipe))
                .containsExactly("name: must not be blank");
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 4})
    void difficultyOutsideRangeTriggersViolation(long invalidDifficulty) {
        Recipe recipe = new Recipe("Cake", invalidDifficulty, Categorie.OTHER);
        Set<String> messages = violationMessages(recipe);

        if (invalidDifficulty < 1) {
            assertThat(messages).contains("difficulty: difficulty moet minimaal 1 zijn");
        } else {
            assertThat(messages).contains("difficulty: difficulty moet maximaal 3 zijn");
        }
    }

    @Test
    void missingCategoryTriggersViolation() {
        Recipe recipe = new Recipe("Cupcake", 2, null);
        assertThat(violationMessages(recipe))
                .contains("categorie: must not be null");
    }
}