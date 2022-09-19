package ch.zli.coworkingspace.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
