package be.vsb.familydashboard.weekmenu;

import be.vsb.familydashboard.recipes.DayRecipeDTO;
import be.vsb.familydashboard.recipes.Recipe;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "weekmenus")
public class WeekMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "start_date")
    @NotNull
    private LocalDate startDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "weekmenu_recipes",
            joinColumns = @JoinColumn(name = "weekmenu_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    @OrderColumn(name = "menu_day")
    private List<Recipe> recipes = new LinkedList<>();

    protected WeekMenu() {
    }

    public WeekMenu(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void addRecipeToWeekMenu(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public List<DayRecipeDTO> getUpcomingDayRecipes() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        int todayIndex = today.getValue() - 1; // Maandag = 1 → index 0

        List<DayRecipeDTO> upcoming = new LinkedList<>();
        for (int i = todayIndex; i < recipes.size(); i++) {
            upcoming.add(new DayRecipeDTO(i, recipes.get(i)));
        }

        return upcoming;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        return "WeekMenu{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", recipes.size=" + recipes.size() +
                ", recipes=" + recipes +
                '}';
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
