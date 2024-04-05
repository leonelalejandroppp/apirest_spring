package cl.leonelalejandroppp.api.users.exceptions;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException() {
        super("El email ya se encuentra en uso");
    }
}
