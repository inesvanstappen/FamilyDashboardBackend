package be.vsb.familydashboard.todo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "todo")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private LocalDate dueDate;

    @NotBlank
    private String task;

    @NotBlank
    private String assignedUser;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Status status;

    protected ToDo() {
    }

    public ToDo(long id, LocalDate dueDate, String task, String assignedUser, Status status) {
        this.id = id;
        this.dueDate = dueDate;
        this.task = task;
        this.assignedUser = assignedUser;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getTask() {
        return task;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public Status getStatus() {
        return status;
    }
}
