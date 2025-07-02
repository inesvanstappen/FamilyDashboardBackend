package be.vsb.familydashboard.todo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "todo")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dueDate;
    private String title;
    private String assignedUser;

    @Enumerated(EnumType.STRING)
    private Status status;

    protected ToDo() {
    }

    public ToDo(long id, LocalDate dueDate, String title, String assignedUser, Status status) {
        this.id = id;
        this.dueDate = dueDate;
        this.title = title;
        this.assignedUser = assignedUser;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public Status getStatus() {
        return status;
    }
}
