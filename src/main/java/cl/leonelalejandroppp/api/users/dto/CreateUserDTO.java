package cl.leonelalejandroppp.api.users.dto;

import cl.leonelalejandroppp.api.phones.dto.CreatePhoneDTO;
import cl.leonelalejandroppp.api.users.entities.UserEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CreateUserDTO {
    @NotNull(message = "El nombre no puede ser nulo")
    private String name;
    @Email(message = "Email no válido")
    private String email;

    @NotNull(message = "La contraseña no puede ser nula")
    private String password;

    @Valid
    @NotNull(message = "Los teléfonos no pueden ser nulos")
    private List<CreatePhoneDTO> phones;

    public UserEntity toUserEntity () {
        return UserEntity.builder()
                .email(this.getEmail())
                .password(this.getPassword())
                .name(this.getName())
                .build();
    }
}
