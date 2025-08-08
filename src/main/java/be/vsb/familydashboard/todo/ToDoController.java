package be.vsb.familydashboard.todo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public List<ToDoDTO> getAllToDos() {
        return toDoService.getAllToDos().stream()
                .map(ToDoDTO::new)
                .toList();
    }

    @GetMapping("{id}")
    public ToDo getToDoById(@PathVariable long id) {
        return toDoService.getToDoById(id).orElseThrow(ToDoNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addToDo(@RequestBody @Valid ToDo toDo) {
        try {
            toDoService.addToDo(toDo);
        } catch (ToDoDuplicateException exception) {
            throw new ToDoDuplicateException(exception.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteToDo(@PathVariable long id) {
        toDoService.deleteToDo(id);
    }
}
