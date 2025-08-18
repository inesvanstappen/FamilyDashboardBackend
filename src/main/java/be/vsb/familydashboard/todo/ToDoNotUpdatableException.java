package be.vsb.familydashboard.todo;

public class ToDoNotUpdatableException extends RuntimeException {
    public ToDoNotUpdatableException() {
        super("Unable to update the to do");
    }
}
