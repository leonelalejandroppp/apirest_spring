package cl.leonelalejandroppp.api.users.services;

import cl.leonelalejandroppp.api.users.exceptions.BadPasswordException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Value("${app.password.regex}")
    private String regex;

    @Value("${app.password.errorRegex}")
    private String errorRegex;

    public void validatePassword (String password) {
        System.out.println(regex);
        System.out.println(errorRegex);
        if(!password.matches(this.regex)) {
            throw new BadPasswordException(this.errorRegex);
        }
    }

}
