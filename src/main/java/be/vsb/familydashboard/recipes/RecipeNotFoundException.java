package be.vsb.familydashboard.recipes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends RuntimeException {
  public RecipeNotFoundException() {
    super("Recipe not found");
  }
}
