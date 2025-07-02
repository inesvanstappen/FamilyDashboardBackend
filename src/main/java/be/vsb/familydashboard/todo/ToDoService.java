package be.vsb.familydashboard.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    public Optional<ToDo> getToDoById(long id) {
        return toDoRepository.findById(id);
    }

    public void addToDo(@Valid ToDo toDo) throws ToDoDuplicateException {
        toDoRepository.findByTaskIgnoreCase(toDo.getTask()).ifPresent(existingToDo -> {
            throw new ToDoDuplicateException("ToDo with task " + toDo.getTask() + " already exists.");
        });

        toDoRepository.save(toDo);
    }

    public void deleteToDo(long id) {
        toDoRepository.findById(id).orElseThrow(ToDoNotFoundException::new);

        toDoRepository.deleteById(id);
    }
}
