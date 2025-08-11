package be.vsb.familydashboard.weekmenu;

import be.vsb.familydashboard.recipes.DayRecipeDTO;

import java.util.List;

public class WeekMenuResponseDTO {
    private Long id;
    private List<DayRecipeDTO> upcomingDayRecipes;

    public WeekMenuResponseDTO(Long id, List<DayRecipeDTO> upcomingDayRecipes) {
        this.id = id;
        this.upcomingDayRecipes = upcomingDayRecipes;
    }

    public Long getId() {
        return id;
    }

    public List<DayRecipeDTO> getUpcomingDayRecipes() {
        return upcomingDayRecipes;
    }
}
