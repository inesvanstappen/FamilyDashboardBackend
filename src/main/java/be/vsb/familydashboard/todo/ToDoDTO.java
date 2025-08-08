package be.vsb.familydashboard.todo;

import java.time.LocalDate;

public class ToDoDTO {
    private Long id;
    private String title;
    private String assignedUserName;
    private String status;
    private LocalDate dueDate;

    public ToDoDTO(ToDo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.assignedUserName = todo.getAssignedUser().getName();
        this.status = todo.getStatus();
        this.dueDate = todo.getDueDate();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAssignedUserName() {
        return assignedUserName;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
