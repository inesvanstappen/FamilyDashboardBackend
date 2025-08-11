package be.vsb.familydashboard.weekmenu;

import be.vsb.familydashboard.recipes.DayRecipeDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class WeekMenuService {
    private final WeekMenuRepository weekMenuRepository;

    public WeekMenuService(WeekMenuRepository weekMenuRepository) {
        this.weekMenuRepository = weekMenuRepository;
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
}
