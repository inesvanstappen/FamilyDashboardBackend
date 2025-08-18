package be.vsb.familydashboard.weekmenu;

import be.vsb.familydashboard.recipes.DayRecipeDTO;
import be.vsb.familydashboard.recipes.Recipe;
import be.vsb.familydashboard.recipes.RecipeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class WeekMenuService {
    private final WeekMenuRepository weekMenuRepository;
    private final RecipeService recipeService;

    public WeekMenuService(WeekMenuRepository weekMenuRepository, RecipeService recipeService) {
        this.weekMenuRepository = weekMenuRepository;
        this.recipeService = recipeService;
    }

    public Optional<WeekMenu> getWeekMenuById(long id) {
        return weekMenuRepository.findById(id);
    }

    public Optional<WeekMenu> getCurrentWeekMenu() {
        LocalDate today = LocalDate.now();
        return weekMenuRepository
                .findFirstByStartDateLessThanEqualOrderByStartDateDesc(today)
                .filter(wm -> !today.isAfter(wm.getStartDate().plusDays(6)));
    }

    public void addWeekMenu(LocalDate startDate, List<Long> recipeIds) {
        WeekMenu weekMenu = new WeekMenu(startDate);

        for (Long recipeId : recipeIds) {
            Optional<Recipe> recipe = recipeService.getRecipeById(recipeId);
            if(!recipe.isEmpty()) {
                Recipe r = recipe.get();
                weekMenu.addRecipeToWeekMenu(r);
            } else {
                continue;
            }
        }

        weekMenuRepository.save(weekMenu);
    }
}
