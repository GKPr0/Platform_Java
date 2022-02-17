package cz.tul.controllers.exceptions;

/**
 * Created by akasa on 21.4.2015.
 */
public class APIErrorMessage {

    private final String message;

    public APIErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
