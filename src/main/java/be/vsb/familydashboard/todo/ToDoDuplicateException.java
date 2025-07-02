package be.vsb.familydashboard.todo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ToDoDuplicateException extends RuntimeException {
  public ToDoDuplicateException(String message) {
    super(message);
  }
}
