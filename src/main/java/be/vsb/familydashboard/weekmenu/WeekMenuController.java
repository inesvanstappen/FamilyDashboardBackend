package be.vsb.familydashboard.weekmenu;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weekmenu")
public class WeekMenuController {

    private final WeekMenuService weekMenuService;

    public WeekMenuController(WeekMenuService weekMenuService) {
        this.weekMenuService = weekMenuService;
    }

    @GetMapping
    public List<WeekMenu> getWeekMenu() {
        return weekMenuService.getWeekMenu();
    }

    @GetMapping("{id}")
    public WeekMenu getWeekMenuById(@PathVariable long id) {
        return weekMenuService.getWeekMenuById(id).orElseThrow(WeekMenuNotFound::new);
    }

}
