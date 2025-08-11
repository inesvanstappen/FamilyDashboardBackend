package be.vsb.familydashboard.recipes;

public class DayRecipeDTO {
    private int dayIndex;
    private Recipe recipe;

    public DayRecipeDTO(int dayIndex, Recipe recipe) {
        this.dayIndex = dayIndex;
        this.recipe = recipe;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}

