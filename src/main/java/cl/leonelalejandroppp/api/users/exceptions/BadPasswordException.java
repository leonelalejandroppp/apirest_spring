package cl.leonelalejandroppp.api.users.exceptions;

public class BadPasswordException extends RuntimeException {
    public BadPasswordException(String message) {
        super(message);
    }
}
