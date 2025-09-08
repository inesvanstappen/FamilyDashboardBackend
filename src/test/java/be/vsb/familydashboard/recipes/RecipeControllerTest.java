package be.vsb.familydashboard.recipes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "API_KEY_WEATHERAPI=dummy-test-key")
@AutoConfigureMockMvc
class RecipeControllerTest {
    private static final String RECIPE_TABLE = "recipes";

    @Autowired
    private MockMvcTester mockMvcTester;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private int countRecordsInRecipeTables() {
        return JdbcTestUtils.countRowsInTable(jdbcTemplate, RECIPE_TABLE);
    }

    private int findIdByName(String name) {
        return jdbcTemplate.queryForObject("SELECT id FROM recipes WHERE name = ?", Integer.class, name);
    }

    @BeforeEach
    void setUp() {
        JdbcTestUtils.deleteFromTables(
                jdbcTemplate,
                "weekmenu_recipes",
                RECIPE_TABLE
        );

        jdbcTemplate.update(
                "INSERT INTO recipes (name, difficulty, categorie) VALUES (?, ?, ?)",
                "Recipe", 3, "DESSERT"
        );
    }

    @Test
    void findOneSpecificRecipe() {
        long id = findIdByName("Recipe");

        assertThat(mockMvcTester.get().uri("/recipes/{id}", id))
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$.name")
                .isEqualTo("Recipe");
    }

    @Test
    void findOneNonExistingRecipe() {
        assertThat(mockMvcTester.get().uri("/recipes/{id}", Long.MAX_VALUE))
                .hasStatus(HttpStatus.NOT_FOUND);
    }

    @Test
    void addOneRecipeMakesTheCountGoUp() {
        int before = countRecordsInRecipeTables();

        String newRecipeJson = """
                {
                  "name":       "Another Recipe",
                  "difficulty": 2,
                  "categorie":  "OTHER"
                }
                """;

        assertThat(
                mockMvcTester.post()
                        .uri("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newRecipeJson)
        ).hasStatus(HttpStatus.CREATED);

        assertThat(countRecordsInRecipeTables()).isEqualTo(before + 1);
    }

    @Test
    void countFindsTheRightNumberOfRecipes() {
        assertThat(mockMvcTester.get().uri("/recipes"))
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$.length()")
                .isEqualTo(countRecordsInRecipeTables());
    }

    @Test
    void deleteOneRecipeMakesTheCountGoDown() {
        jdbcTemplate.update(
                "INSERT INTO recipes (name, difficulty, categorie) VALUES (?, ?, ?)",
                "ToDelete", 1, "DESSERT"
        );

        int before = countRecordsInRecipeTables();
        int idToDelete = findIdByName("ToDelete");

        assertThat(mockMvcTester.delete().uri("/recipes/{id}", idToDelete))
                .hasStatus(HttpStatus.OK);

        int after = countRecordsInRecipeTables();

        assertThat(after).isEqualTo(before - 1);
    }

    @Test
    void deleteNonExistingRecipeReturnsNotFound() {
        assertThat(
                mockMvcTester.delete()
                        .uri("/recipes/{id}", Long.MAX_VALUE)
        ).hasStatus(HttpStatus.NOT_FOUND);
    }

    @Test
    void updateRecipeChangesName() {
        int id = findIdByName("Recipe");

        String updateJson = """
            {
              "id":         %d,
              "name":       "Updated Recipe",
              "difficulty": 3,
              "categorie":  "DESSERT"
            }
            """.formatted(id);

        assertThat(
                mockMvcTester.post()
                        .uri("/recipes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson)
        ).hasStatus(HttpStatus.OK);

        assertThat(
                mockMvcTester.get()
                        .uri("/recipes/{id}", id)
        ).hasStatusOk()
                .bodyJson()
                .extractingPath("$.name")
                .isEqualTo("Updated Recipe");
    }

    @Test
    void updateNonExistingRecipeReturnsNotFound() {
        String updateJson = """
                {
                  "name":       "Nope",
                  "difficulty": 1,
                  "categorie":  "OTHER"
                }
                """;

        assertThat(
                mockMvcTester.post()
                        .uri("/recipes/{id}", Long.MAX_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson)
        ).hasStatus(HttpStatus.NOT_FOUND);
    }

    @Test
    void updateInvalidRecipeReturnsBadRequest() {
        int id = findIdByName("Recipe");
        String invalidJson = """
                {
                  "name":       "",
                  "difficulty": 2,
                  "categorie":  "MAIN"
                }
                """;

        assertThat(
                mockMvcTester.post()
                        .uri("/recipes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson)
        ).hasStatus(HttpStatus.BAD_REQUEST);
    }
}
