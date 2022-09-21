package ch.zli.coworkingspace.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
