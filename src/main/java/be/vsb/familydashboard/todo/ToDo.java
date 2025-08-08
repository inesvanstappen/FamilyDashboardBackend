package be.vsb.familydashboard.todo;

import be.vsb.familydashboard.users.User;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "todos")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_user_id", nullable = false)
    private User assignedUser;

    @Column(nullable = false)
    private String status;

    // Constructors
    public ToDo() {
    }

    public ToDo(LocalDate dueDate, String title, User assignedUser, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
