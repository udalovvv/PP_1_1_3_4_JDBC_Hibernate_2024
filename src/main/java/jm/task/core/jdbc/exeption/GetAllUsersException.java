package jm.task.core.jdbc.exeption;

public class GetAllUsersException extends RuntimeException{
    public GetAllUsersException(String message, Throwable cause) {
        super(message, cause);
    }
}
