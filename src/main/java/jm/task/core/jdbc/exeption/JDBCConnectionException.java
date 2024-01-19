package jm.task.core.jdbc.exeption;

public class JDBCConnectionException extends RuntimeException{
    public JDBCConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
