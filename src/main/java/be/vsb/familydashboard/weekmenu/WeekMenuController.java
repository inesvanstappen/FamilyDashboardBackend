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
    public WeekMenuResponseDTO getWeekMenu() {
        return weekMenuService.getCurrentWeekMenu()
                .map(weekMenu -> new WeekMenuResponseDTO(weekMenu.getId(), weekMenu.getUpcomingDayRecipes()))
                .orElseThrow(WeekMenuNotFound::new);
    }

    @GetMapping("{id}")
    public WeekMenu getWeekMenuById(@PathVariable long id) {
        return weekMenuService.getWeekMenuById(id).orElseThrow(WeekMenuNotFound::new);
    }
}
