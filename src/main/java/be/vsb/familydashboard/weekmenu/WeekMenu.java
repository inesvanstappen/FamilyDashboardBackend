package be.vsb.familydashboard.weekmenu;

import be.vsb.familydashboard.recipes.Recipe;
import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "weekmenus")
public class WeekMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "weekmenu_recipes",
            joinColumns = @JoinColumn(name = "weekmenu_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    @OrderColumn(name = "menu_day")
    private List<Recipe> weekMenu = new LinkedList<>();

    protected WeekMenu() {
    }

    public void addRecipeToWeekMenu(Recipe recipe) {
        this.weekMenu.add(recipe);
    }

    public List<Recipe> getWeekMenu() {
        return this.weekMenu;
    }

    public Recipe getRecipesForDay(int day) {
        return this.weekMenu.get(day);
    }

    public List<Recipe> switchRecipes(int day1, int day2) {
        Recipe recipe1 = this.weekMenu.get(day1);
        Recipe recipe2 = this.weekMenu.get(day2);

        this.weekMenu.set(day1, recipe2);
        this.weekMenu.set(day2, recipe1);

        return List.of(recipe1, recipe2);
    }
}
