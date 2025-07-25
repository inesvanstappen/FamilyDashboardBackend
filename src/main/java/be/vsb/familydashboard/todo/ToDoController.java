package be.vsb.familydashboard.todo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public List<ToDo> getAllToDos() {
        return toDoService.getAllToDos();
    }

    @GetMapping("{id}")
    public ToDo getToDoById(@PathVariable long id) {
        return toDoService.getToDoById(id).orElseThrow(ToDoNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addToDo(@RequestBody @Valid ToDo toDo) {
        try{
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
