package be.vsb.familydashboard.todo;

import be.vsb.familydashboard.users.User;
import be.vsb.familydashboard.users.UserRepository;
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
    private final UserRepository userRepository;

    public ToDoController(ToDoService toDoService, UserRepository userRepository) {
        this.toDoService = toDoService;
        this.userRepository = userRepository;
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
    public void addToDo(@RequestBody @Valid ReceivedToDoDTO toDoDTO) {
        try {

            if (toDoDTO.getAssignedUserId() == null) {
                throw new IllegalArgumentException("Assigned user ID must not be null");
            }

            User user = userRepository.findById(toDoDTO.getAssignedUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            ToDo toDo = new ToDo(
                    toDoDTO.getDueDate(),
                    toDoDTO.getTitle(),
                    user,
                    toDoDTO.getStatus()
            );

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
