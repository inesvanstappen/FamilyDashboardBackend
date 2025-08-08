package be.vsb.familydashboard.weekmenu;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class WeekMenuService {
    private final WeekMenuRepository weekMenuRepository;

    public WeekMenuService(WeekMenuRepository weekMenuRepository) {
        this.weekMenuRepository = weekMenuRepository;
    }

    public List<WeekMenu> getWeekMenu() {
        return weekMenuRepository.findAll();
    }

    public Optional<WeekMenu> getWeekMenuById(long id) {
        return weekMenuRepository.findById(id);
    }

    public Optional<WeekMenu> getLatestWeekMenu() {
        return weekMenuRepository.findFirstByOrderByIdDesc();
    }
}
