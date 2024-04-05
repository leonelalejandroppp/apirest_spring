package cl.leonelalejandroppp.api.users.dto;

import cl.leonelalejandroppp.api.phones.entities.PhoneEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserListDTO {
    private String id;
    private String name;
    private String email;
    private List<PhoneEntity> phones;
}
