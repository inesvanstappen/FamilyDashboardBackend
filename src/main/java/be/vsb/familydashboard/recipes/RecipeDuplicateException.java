package be.vsb.familydashboard.recipes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RecipeDuplicateException extends RuntimeException {
  public RecipeDuplicateException(String message) {
    super(message);
  }
}
