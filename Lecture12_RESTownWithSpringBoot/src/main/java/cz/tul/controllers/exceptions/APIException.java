package cz.tul.controllers.exceptions;

public class APIException extends RuntimeException {

    public APIException(String message) {
        super(message);
    }
}
