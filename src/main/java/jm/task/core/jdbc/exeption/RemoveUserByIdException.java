package jm.task.core.jdbc.exeption;

public class RemoveUserByIdException extends RuntimeException{
    public RemoveUserByIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
