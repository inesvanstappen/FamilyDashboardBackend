package be.vsb.familydashboard.todo;

import java.time.LocalDate;

public class ToDoDTO {
    private Long id;
    private String title;
    private String assignedUserName;
    private Long assignedUserId;
    private Status status;
    private LocalDate dueDate;

    public ToDoDTO(ToDo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.assignedUserName = todo.getAssignedUser().getName();
        this.status = todo.getStatus();
        this.dueDate = todo.getDueDate();
        this.assignedUserId = todo.getAssignedUser().getId();
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

    public Status getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Long getAssignedUserId() {
        return assignedUserId;
    }
}
