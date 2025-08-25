package be.vsb.familydashboard.todo;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ReceivedToDoDTO {
    @NotNull
    private LocalDate dueDate;

    @NotNull
    private String title;

    @NotNull
    private Long assignedUserId;

    @NotNull
    private Status status;

    public ReceivedToDoDTO() {
    }

    public ReceivedToDoDTO(String title, LocalDate dueDate, Long assignedUserId, Status status) {
        this.title = title;
        this.dueDate = dueDate;
        this.assignedUserId = assignedUserId;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public Status getStatus() {
        return status;
    }
}
