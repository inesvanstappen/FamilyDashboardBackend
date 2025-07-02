package be.vsb.familydashboard.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    Optional<ToDo> findByTaskIgnoreCase(String task);
}
