package be.vsb.familydashboard.weekmenu;

import java.time.LocalDate;
import java.util.List;

public record ReceivedWeekMenuDTO(LocalDate startDate, List<Long> recipeIds) {}