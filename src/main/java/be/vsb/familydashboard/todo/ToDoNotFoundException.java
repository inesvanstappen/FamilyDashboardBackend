package be.vsb.familydashboard.todo;

public class ToDoNotFoundException extends RuntimeException {
    public ToDoNotFoundException() {
        super("ToDo not found");
    }
}
