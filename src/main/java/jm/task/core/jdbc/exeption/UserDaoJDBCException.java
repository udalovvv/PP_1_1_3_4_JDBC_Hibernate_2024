package jm.task.core.jdbc.exeption;

public class UserDaoJDBCException extends RuntimeException{

    public UserDaoJDBCException(String message, Throwable cause) {
        super(message, cause);
    }
}
