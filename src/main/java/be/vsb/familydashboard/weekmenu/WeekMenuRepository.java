package be.vsb.familydashboard.weekmenu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WeekMenuRepository extends JpaRepository<WeekMenu, Long> {
    Optional<WeekMenu> findFirstByStartDateLessThanEqualOrderByStartDateDesc(LocalDate date);
}
