package be.vsb.familydashboard.weekmenu;

import be.vsb.familydashboard.recipes.DayRecipeDTO;
import be.vsb.familydashboard.recipes.Recipe;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/weekmenu")
public class WeekMenuController {

    private final WeekMenuService weekMenuService;

    public WeekMenuController(WeekMenuService weekMenuService) {
        this.weekMenuService = weekMenuService;
    }

    @GetMapping
    public WeekMenu getWeekMenu() {
        return weekMenuService.getLatestWeekMenu().orElseThrow(WeekMenuNotFound::new);
    }

    @GetMapping("{id}")
    public WeekMenu getWeekMenuById(@PathVariable long id) {
        return weekMenuService.getWeekMenuById(id).orElseThrow(WeekMenuNotFound::new);
    }

    @GetMapping("/upcoming")
    public List<DayRecipeDTO> getUpcomingDayRecipes() {
        WeekMenu weekMenu = weekMenuService.getLatestWeekMenu().orElseThrow(WeekMenuNotFound::new);
        return weekMenu.getUpcomingDayRecipes();
    }
}
