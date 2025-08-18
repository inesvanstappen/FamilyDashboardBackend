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

    public Optional<ToDo> findToDoById(long id) {
        return toDoRepository.findById(id);
    }

    @Transactional
    public void addToDo(@Valid ToDo toDo) throws ToDoDuplicateException {
        toDoRepository.findByTitleIgnoreCase(toDo.getTitle()).ifPresent(existingToDo -> {
            throw new ToDoDuplicateException("ToDo with task " + toDo.getTitle() + " already exists.");
        });

        toDoRepository.save(toDo);
    }

    public void deleteToDo(long id) {
        toDoRepository.findById(id).orElseThrow(ToDoNotFoundException::new);

        toDoRepository.deleteById(id);
    }

    @Transactional
    public void updateToDo(@Valid ToDo toDo) {
        toDoRepository.save(toDo);
    }
}
