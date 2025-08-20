package be.vsb.familydashboard.weekmenu;

import be.vsb.familydashboard.recipes.Recipe;
import be.vsb.familydashboard.recipes.RecipeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.OPTIONS
        })
@RestController
@RequestMapping("/weekmenu")
public class WeekMenuController {

    private final WeekMenuService weekMenuService;
    private final RecipeService recipeService;

    public WeekMenuController(WeekMenuService weekMenuService, RecipeService recipeService) {
        this.weekMenuService = weekMenuService;
        this.recipeService = recipeService;
    }

    @GetMapping("{id}")
    public WeekMenu getWeekMenuById(@PathVariable long id) {
        return weekMenuService.getWeekMenuById(id).orElseThrow(WeekMenuNotFound::new);
    }

    @GetMapping
    public WeekMenuResponseDTO getWeekMenu() {
        return weekMenuService.getCurrentWeekMenu()
                .map(weekMenu -> new WeekMenuResponseDTO(weekMenu.getId(), weekMenu.getUpcomingDayRecipes()))
                .orElseThrow(WeekMenuNotFound::new);
    }

    @PostMapping
    public void addWeekMenu(@RequestBody ReceivedWeekMenuDTO payload) {
        weekMenuService.addWeekMenu(payload.startDate(), payload.recipeIds());
    }

    @PutMapping("{id}")
    public void updateWeekMenu(@PathVariable long id, @RequestBody @Valid ReceivedWeekMenuDTO payload) {
        WeekMenu weekMenu = weekMenuService.getWeekMenuById(id).orElseThrow(WeekMenuNotFound::new);

        weekMenu.setStartDate(payload.startDate());

        List<Recipe> recipes = payload.recipeIds().stream()
                .map(recipeService::getRecipeById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toCollection(LinkedList::new));

        weekMenu.setRecipes(recipes);

        weekMenuService.updateWeekMenu(weekMenu);

    }
}
