package be.vsb.familydashboard.weekmenu;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WeekMenuNotFound extends RuntimeException {
    public WeekMenuNotFound() {
      super("WeekMenu not found");
    }
}
