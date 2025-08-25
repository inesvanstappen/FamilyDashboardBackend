package be.vsb.familydashboard.todo;

import be.vsb.familydashboard.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "todos")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "due_date", nullable = false)
    @NotNull
    private LocalDate dueDate;

    @Column(nullable = false)
    @NotBlank
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "assigned_user_id", nullable = false)
    @NotNull
    private User assignedUser;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // Constructors
    public ToDo() {
    }

    public ToDo(LocalDate dueDate, String title, User assignedUser, Status status) {
        this.dueDate = dueDate;
        this.title = title;
        this.assignedUser = assignedUser;
        this.status = status;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", dueDate=" + dueDate +
                ", title='" + title + '\'' +
                ", assignedUser={" + assignedUser.getName() + ", " + assignedUser.getId() +
                "}, status='" + status + '\'' +
                '}';
    }
}
